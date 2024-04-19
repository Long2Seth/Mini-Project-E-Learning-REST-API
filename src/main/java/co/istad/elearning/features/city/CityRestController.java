package co.istad.elearning.features.city;

import co.istad.elearning.features.city.dto.CityResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CityRestController {
    private final CityService cityService;

    @GetMapping("/api/v1/cities")
    public List<CityResponse> getAllCities(
            @RequestParam(required = false) String filter,
            @RequestParam(required = false) String sortBy) {
        return cityService.getAllCities(filter, sortBy);
    }
    @GetMapping("/api/v1/countries/{iso}/cities")
    public List<CityResponse> getAllCitiesInCountry(@PathVariable String iso) {
        return cityService.getAllCitiesInCountry(iso);
    }
}