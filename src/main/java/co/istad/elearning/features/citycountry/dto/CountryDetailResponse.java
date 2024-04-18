package co.istad.elearning.features.citycountry.dto;

import java.util.List;

public record CountryDetailResponse(

    String flag,

    String iso,

    String name,

    String niceName,

    String numCode,

    String phoneCode,

    List<CityResponse> cities
) {
}
