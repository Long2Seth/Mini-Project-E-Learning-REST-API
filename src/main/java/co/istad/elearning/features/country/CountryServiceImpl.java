package co.istad.elearning.features.country;


import co.istad.elearning.domain.Country;
import co.istad.elearning.features.country.dto.CountryResponse;
import co.istad.elearning.mapper.CountryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService{
    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;
    @Override
    public List<CountryResponse> getAllCountries(String filter, String sortBy) {
        List<Country> countries;
        if (filter != null && !filter.isEmpty()) {
            countries = countryRepository.findByNameContainingIgnoreCase(filter);
        } else {
            countries = countryRepository.findAll();
        }
        if (sortBy != null && sortBy.equals("name")) {
            countries.sort(Comparator.comparing(Country::getName));
        }
        return countries.stream()
                .map(countryMapper::toCountryResponse)
                .collect(Collectors.toList());
    }
}