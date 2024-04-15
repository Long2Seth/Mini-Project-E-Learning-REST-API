package co.istad.elearning.features.instuctor.dto;

import co.istad.elearning.features.user.dto.UserDetailResponse;

public record InstructorResponse(
        String biography,

        String github,

        String jobTitle,

        String linkIn,

        String website,

        UserDetailResponse userDetailResponse

) {
}
