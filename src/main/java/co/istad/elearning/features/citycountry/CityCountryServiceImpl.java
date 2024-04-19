package co.istad.elearning.features.citycountry;

import co.istad.elearning.domain.City;
import co.istad.elearning.domain.Country;
import co.istad.elearning.features.citycountry.dto.CityResponse;
import co.istad.elearning.features.citycountry.dto.CountryDetailResponse;
import co.istad.elearning.mapper.CityMapper;
import co.istad.elearning.mapper.CountryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityCountryServiceImpl implements CityCountryService {
    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;
    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    @Override
    public void fetchAndSaveData() {

    }

    @Override
    public List<CityResponse> getAllCities(String name, Sort.Direction sort) {
        List<City> cities = cityRepository.findAll(Sort.by(sort, "name"));

        return cities.stream()
                .filter(city -> name == null || city.getName().toLowerCase().contains(name.toLowerCase()))
                .map(cityMapper::toCityResponse)
                .collect(Collectors.toList());
    }


    @Override
    public List<CityResponse> getCitiesByCountryIso(String iso) {
        List<City> cities = cityRepository.findCityByCountry_Iso(iso);
        if (cities == null) {
            throw new NoSuchElementException("Country not found");
        }
        return cityMapper.toCityResponseList(cities);
    }


    @Override
    public List<CountryDetailResponse> getAllCountries(String name, Sort.Direction sort) {

        List<Country> countries = countryRepository.findAll(Sort.by(sort, "name"));

        return countries.stream()
                .filter(country -> name == null || country.getName().toLowerCase().contains(name.toLowerCase()))
                .map(countryMapper::toCountryDetailResponse)
                .collect(Collectors.toList());
    }

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
