package co.istad.elearning.features.citycountry.dto;

import co.istad.elearning.domain.City;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CountryResponse{

    private boolean error;
    private String msg;
    private CountryResponse[] data;

    private String country;
    private String iso3;

    @JsonProperty("iso3")
    public String getIso3() {
        return iso3;
    }

    private List<String> cities;








}
