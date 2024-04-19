package co.istad.elearning.features.country;

import co.istad.elearning.features.country.dto.CountryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/countries")
@RequiredArgsConstructor
public class CountryRestController {
    private final CountryService countryService;
    @GetMapping
    public List<CountryResponse> getAllCountries(
            @RequestParam(required = false) String filter,
            @RequestParam(required = false, defaultValue = "name") String sortBy
    ) {
        return countryService.getAllCountries(filter, sortBy);
    }
}

