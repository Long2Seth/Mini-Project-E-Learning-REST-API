package co.istad.elearning.features.user.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;


public record UserDetailResponse(

         String uuid,

         String address1,

         String address2,

         LocalDate dob,

         String email,

         String familyName,

         String gender,

         String givenName,

         String nationalIdCard,

         String password,

         String phoneNumber,

         String profile,

         String username,

         List<RoleNameResponse> roles

) {
}
