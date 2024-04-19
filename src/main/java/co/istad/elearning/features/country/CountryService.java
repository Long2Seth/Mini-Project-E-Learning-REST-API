package co.istad.elearning.features.country;

import co.istad.elearning.features.country.dto.CountryResponse;

import java.util.List;

public interface CountryService {
    List<CountryResponse> getAllCountries(String filter, String sortBy);
}
