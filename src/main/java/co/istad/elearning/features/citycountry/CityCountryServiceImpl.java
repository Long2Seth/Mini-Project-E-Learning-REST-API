package co.istad.elearning.features.citycountry;

import co.istad.elearning.domain.Country;
import co.istad.elearning.features.citycountry.dto.CountryResponse;
import co.istad.elearning.mapper.CountryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CityCountryServiceImpl {

//    private final CountryRepository countryRepository;
//
//    public void fetchAndSaveData() {
//        RestTemplate restTemplate = new RestTemplate();
//        // Assuming the API returns a list of objects with name and population fields
//        CountryResponse[] countryResponses = restTemplate.getForObject("https://countriesnow.space/api/v0.1/countries/Codes", CountryResponse[].class);
//
//        for (CountryResponse countryResponse : countryResponses) {
//            // Transform API data to domain model
//            Country country = new Country();
//            country.setName(apiResponse.getName());
//            country.setPopulation(apiResponse.getPopulation());
//
//            // Save to database
//            countryRepository.save(country);
//        }
//    }

}
