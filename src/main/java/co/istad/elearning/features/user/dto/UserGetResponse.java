package co.istad.elearning.features.user.dto;

import co.istad.elearning.features.citycountry.dto.CityResponse;
import co.istad.elearning.features.citycountry.dto.CountryResponse;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;


@Builder
public record UserGetResponse(
        String username,
        String email,
        String nationalIdCard,
        String phoneNumber,
        String gender,
        boolean isVerified,
        boolean isDeleted,
        String city,
        String country,
        List<RoleNameResponse> roles

) {
}
