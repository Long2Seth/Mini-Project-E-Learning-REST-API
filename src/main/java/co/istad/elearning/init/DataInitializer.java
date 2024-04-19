package co.istad.elearning.init;

import co.istad.elearning.domain.*;
import co.istad.elearning.features.category.CategoryRepository;
import co.istad.elearning.features.citycountry.CityRepository;
import co.istad.elearning.features.citycountry.CountryRepository;
import co.istad.elearning.features.citycountry.dto.CountryInitInfoResponse;
import co.istad.elearning.features.citycountry.dto.CountryResponse;
import co.istad.elearning.features.user.AuthorityRepository;
import co.istad.elearning.features.user.RoleRepository;
import co.istad.elearning.features.user.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.*;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final RoleRepository roleRepository;
    private final CategoryRepository categoryRepository;
    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;
    private final AuthorityRepository authorityRepository;
    private final UserRepository userRepository;


    @PostConstruct
    void initRole() {

        if (roleRepository.count() < 1) {
            Role user = new Role();
            user.setName("USER");

            Role student = new Role();
            student.setName("STUDENT");

            Role instructor = new Role();
            instructor.setName("INSTRUCTOR");

            Role admin = new Role();
            admin.setName("ADMIN");

            roleRepository.saveAll(
                    List.of(user, student, instructor, admin)
            );
        }

    }

    @PostConstruct
    public void init() {
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
        List<String> userAuthorities = Arrays.asList("user:read", "user:write", "user:update", "progress:read", "elearning:read");
        List<String> adminAuthorities = Arrays.asList("user:read", "user:write", "user:delete", "user:update", "progress:read", "progress:write", "elearning:read", "elearning:write", "elearning:update", "elearning:delete");
        List<String> studentAuthorities = Collections.singletonList("progress:write");
        List<String> instructorAuthorities = Arrays.asList("elearning:write", "elearning:delete", "elearning:update");

        Map<String, List<String>> roleAuthoritiesMap = new HashMap<>();
        roleAuthoritiesMap.put("USER", userAuthorities);
        roleAuthoritiesMap.put("ADMIN", adminAuthorities);
        roleAuthoritiesMap.put("STUDENT", studentAuthorities);
        roleAuthoritiesMap.put("INSTRUCTOR", instructorAuthorities);

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
                    authority.getRoles().add(role); // Ensure both sides of the relationship are managed
                }
                roleRepository.save(role);
            }
        }
    }



    @PostConstruct
    void initCategory() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category()
                .setName("Java")
                .setAlias("java")
                .setIcon("java-icon.png")
                .setIsDeleted(false));

        categories.add(new Category()
                .setName("Big Data")
                .setAlias("big-data")
                .setIcon("big-data-icon.png")
                .setIsDeleted(false));

        categories.add(new Category()
                .setName("Python")
                .setAlias("python")
                .setIcon("python-icon.png")
                .setIsDeleted(false));

        categoryRepository.saveAll(categories);
    }

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
    void initUser() {
        List<User> users = new ArrayList<>(){
            {
                Role STUDENT = roleRepository.findByName("STUDENT").orElseThrow(() -> new RuntimeException("Role not found"));
                Role INSTRUCTOR = roleRepository.findByName("INSTRUCTOR").orElseThrow(() -> new RuntimeException("Role not found"));
                add(new User()
                        .setUuid(UUID.randomUUID().toString())
                        .setAddress1("Address 1")
                        .setAddress2("Address 2")
                        .setDob(LocalDate.of(1990, 1, 1))
                        .setEmail("user1@example.com")
                        .setFamilyName("User1FamilyName")
                        .setGender("Male")
                        .setGivenName("User1GivenName")
                        .setNationalIdCard("12345678")
                        .setPassword("password1")
                        .setPhoneNumber("1234567890")
                        .setProfile("user1-profile")
                        .setUsername("user1")
                        .setIsVerified(true)
                        .setIsDeleted(false)
                        .setCity(cityRepository.findById(1).orElse(null)) // Assuming you have cityRepository injected and city with ID 1 exists
                        .setCountry(countryRepository.findById(1).orElse(null)) // Assuming you have countryRepository injected and country with ID 1 exists
                        .setRoles(List.of(INSTRUCTOR))
                );

                add(new User()
                        .setUuid(UUID.randomUUID().toString())
                        .setAddress1("Address 3")
                        .setAddress2("Address 4")
                        .setDob(LocalDate.of(1995, 5, 15))
                        .setEmail("user2@example.com")
                        .setFamilyName("User2FamilyName")
                        .setGender("Female")
                        .setGivenName("User2GivenName")
                        .setNationalIdCard("87654321")
                        .setPassword("password2")
                        .setPhoneNumber("9876543210")
                        .setProfile("user2-profile")
                        .setUsername("user2")
                        .setIsVerified(true)
                        .setIsDeleted(false)
                        .setCity(cityRepository.findById(2).orElse(null)) // Assuming you have cityRepository injected and city with ID 2 exists
                        .setCountry(countryRepository.findById(2).orElse(null)) // Assuming you have countryRepository injected and country with ID 2 exists
                        .setRoles(List.of(STUDENT))
                );


            }

        };
        userRepository.saveAll(users);


    }


}
