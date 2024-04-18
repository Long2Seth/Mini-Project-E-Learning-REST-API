package co.istad.elearning.init;

import co.istad.elearning.domain.City;
import co.istad.elearning.domain.Country;
import co.istad.elearning.domain.Role;
import co.istad.elearning.features.citycountry.CityRepository;
import co.istad.elearning.features.citycountry.CountryRepository;
import co.istad.elearning.features.citycountry.dto.CountryInitInfoResponse;
import co.istad.elearning.features.citycountry.dto.CountryResponse;
import co.istad.elearning.features.user.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final RoleRepository roleRepository;
    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;


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

}
