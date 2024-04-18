package co.istad.elearning.init;

import co.istad.elearning.domain.Authority;
import co.istad.elearning.domain.City;
import co.istad.elearning.domain.Country;
import co.istad.elearning.domain.Role;
import co.istad.elearning.features.citycountry.CityRepository;
import co.istad.elearning.features.citycountry.CountryRepository;
import co.istad.elearning.features.citycountry.dto.CountryInitInfoResponse;
import co.istad.elearning.features.citycountry.dto.CountryResponse;
import co.istad.elearning.features.user.AuthorityRepository;
import co.istad.elearning.features.user.RoleRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
@RequiredArgsConstructor
@Transactional
public class DataInitializer {

    private final RoleRepository roleRepository;
    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;
    private final AuthorityRepository authorityRepository;


//    @PostConstruct
//    void initRole() {
//        if (roleRepository.count() < 1) {
//            Role user = new Role();
//            user.setName("USER");
//
//            Role student = new Role();
//            student.setName("STUDENT");
//
//            Role instructor = new Role();
//            instructor.setName("INSTRUCTOR");
//
//            Role admin = new Role();
//            admin.setName("ADMIN");
//
//            roleRepository.saveAll(
//                    List.of(user, student, instructor, admin)
//            );
//        }
//
//    }

    @PostConstruct
    void initData() {
        if(countryRepository.count() < 5){
            RestTemplate restTemplate = new RestTemplate();

            CountryResponse countryResponses = restTemplate.getForObject("https://countriesnow.space/api/v0.1/countries", CountryResponse.class);

            if (countryResponses != null) {
                int processedCountries = 0;
                for (CountryResponse countryResponse : countryResponses.getData()) {
                    if (processedCountries >= 5) {
                        break;
                    }
                    Country country = new Country();
                    country.setName(countryResponse.getCountry());
                    country.setIso(countryResponse.getIso3());

                    Country savedCountry = countryRepository.save(country);

                    List<String> cities = countryResponse.getCities();
                    for (String cityName : cities) {
                        City city = new City();
                        city.setName(cityName);
                        city.setCountry(savedCountry);
                        cityRepository.save(city);
                    }
                    processedCountries++;
                }
            } else {
                System.out.println("Error fetching data.");
            }

            CountryInitInfoResponse countryInitInfoResponse = restTemplate.getForObject("https://countriesnow.space/api/v0.1/countries/info?returns=flag,dialCode", CountryInitInfoResponse.class);

            if (countryInitInfoResponse != null) {

                for(CountryInitInfoResponse countryInfoResponse : countryInitInfoResponse.getData()){
                    Optional<Country> countryOptional = countryRepository.findByName(countryInfoResponse.getName());
                    if (countryOptional.isPresent()) {
                        Country country = countryOptional.get();
                        country.setFlag(countryInfoResponse.getFlag());
                        country.setNiceName(countryInfoResponse.getName());
                        country.setNumCode(countryInfoResponse.getDialCode());
                        country.setPhoneCode(countryInfoResponse.getDialCode());
                        countryRepository.save(country);
                    }
                }
            }else {
                System.out.println("Error fetching data.");
            }
        }

    }


    @PostConstruct
    public void initAuthority() {
        if(authorityRepository.count() < 1){
            List<String> authorities = Arrays.asList("user:read", "user:write", "user:delete", "user:update", "progress:read", "progress:write", "elearning:read", "elearning:write", "elearning:update", "elearning:delete");

            // Create authorities
            List<Authority> authorityList = new ArrayList<>();
            for (String authority : authorities) {
                Authority authorityEntity = new Authority();
                authorityEntity.setName(authority);
                authorityList.add(authorityRepository.save(authorityEntity));
            }

            // Create roles and associate authorities
            List<String> roles = Arrays.asList("USER", "ADMIN", "STUDENT", "INSTRUCTOR");
            Map<String, List<String>> roleAuthoritiesMap = new HashMap<>();
            roleAuthoritiesMap.put("USER", Arrays.asList("user:read", "user:write", "user:update", "progress:read", "elearning:read"));
            roleAuthoritiesMap.put("ADMIN", Arrays.asList("user:read", "user:write", "user:delete", "user:update", "progress:read", "progress:write", "elearning:read", "elearning:write", "elearning:update", "elearning:delete"));
            roleAuthoritiesMap.put("STUDENT", Collections.singletonList("progress:write"));
            roleAuthoritiesMap.put("INSTRUCTOR", Arrays.asList("elearning:write", "elearning:delete", "elearning:update"));

            for (String roleName : roles) {
                Role role = new Role();
                role.setName(roleName);
                List<String> roleAuthorities = roleAuthoritiesMap.get(roleName);
                for (String authorityName : roleAuthorities) {
                    Authority authority = authorityList.stream()
                            .filter(a -> a.getName().equals(authorityName))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("Authority not found: " + authorityName));
                    role.getAuthorities().add(authority);

                }
                roleRepository.save(role);
            }
        }
    }
}
