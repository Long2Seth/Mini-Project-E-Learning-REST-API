package co.istad.elearning.mapper;

import co.istad.elearning.domain.City;
import co.istad.elearning.features.citycountry.dto.CityResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CityMapper {
    CityResponse toCityResponse(City city);

    List<CityResponse> toCityResponseList(List<City> cities);
}
