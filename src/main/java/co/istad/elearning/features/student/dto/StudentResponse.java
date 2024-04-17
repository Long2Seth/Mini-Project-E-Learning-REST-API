package co.istad.elearning.features.student.dto;

import co.istad.elearning.features.user.dto.UserDetailResponse;

public record StudentResponse(
        String highSchool,

        Boolean isBlocked,

        String university,

        UserDetailResponse userDetailResponse
) {
}
