package co.istad.elearning.features.citycountry;

import co.istad.elearning.domain.Country;
import co.istad.elearning.features.citycountry.dto.CityResponse;
import co.istad.elearning.features.citycountry.dto.CountryDetailResponse;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CityCountryService {
    void fetchAndSaveData();

    List<CityResponse> getAllCities(String name, Sort.Direction sort);


   List< CityResponse> getCitiesByCountryIso(String iso);

    List<CountryDetailResponse> getAllCountries(String name, Sort.Direction sort);
}
