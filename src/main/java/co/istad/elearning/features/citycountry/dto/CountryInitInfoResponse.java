package co.istad.elearning.features.citycountry.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CountryInitInfoResponse {
    private boolean error;
    private String msg;
    private CountryInitInfoResponse[] data;
    private String name;
    private String dialCode;
    private String flag;

    @JsonProperty("flag")
    public String getFlag() {
        return flag;
    }

    @JsonProperty("dialCode")
    public String getDialCode() {
        return dialCode;
    }
}
