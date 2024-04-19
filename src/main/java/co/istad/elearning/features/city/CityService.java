package co.istad.elearning.features.city;

import co.istad.elearning.features.city.dto.CityResponse;

import java.util.List;

public interface CityService {
    List<CityResponse> getAllCities(String filter, String sortBy);
    List<CityResponse> getAllCitiesInCountry(String iso);
}
