package co.istad.elearning.mapper;


import co.istad.elearning.domain.Country;
import co.istad.elearning.features.country.dto.CountryResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    CountryResponse toCountryResponse(Country country);
}
