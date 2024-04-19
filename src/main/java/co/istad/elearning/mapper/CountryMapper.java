package co.istad.elearning.mapper;

import co.istad.elearning.domain.City;
import co.istad.elearning.domain.Country;
import co.istad.elearning.features.citycountry.dto.CountryDetailResponse;
import co.istad.elearning.features.citycountry.dto.CountryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CountryMapper {

//    @Mapping(target = "name", source = "country")
    CountryResponse toCountryResponse(Country country);
    CountryDetailResponse toCountryDetailResponse(Country country);

    default List<String> mapCities(List<City> cities) {
        if (cities == null) {
            return null;
        }
        return cities.stream()
                .map(City::getName)
                .collect(Collectors.toList());
    }
}
