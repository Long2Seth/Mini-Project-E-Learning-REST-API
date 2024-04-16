package co.istad.elearning.features.instuctor.dto;

import co.istad.elearning.features.user.dto.UserDetailResponse;
import com.fasterxml.jackson.annotation.JsonCreator;


public record InstructorResponse(
        String biography,

        String github,

        String jobTitle,

        String linkIn,

        String website,

        UserDetailResponse userDetailResponse

) {

}
