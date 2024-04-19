package co.istad.elearning.features.citycountry;

import co.istad.elearning.features.citycountry.dto.CityResponse;
import co.istad.elearning.features.citycountry.dto.CountryDetailResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CityCountryController {
    private final CityCountryService cityCountryService;

    @GetMapping("/cities")
    public List<CityResponse> getAllCities(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "ASC") Sort.Direction sort) {
        return cityCountryService.getAllCities(name, sort);
    }

    @GetMapping("/countries/{iso}/cities")
    public List<CityResponse> getAllCitiesInCountry(@PathVariable String iso) {
        return cityCountryService.getCitiesByCountryIso(iso);
    }


    @GetMapping("/countries")
    @Operation(summary = "Get all countries")
    public List<CountryDetailResponse> getAllCountries(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "ASC") Sort.Direction sort) {
        return cityCountryService.getAllCountries(name, sort);
    }


}
