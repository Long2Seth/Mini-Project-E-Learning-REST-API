package co.istad.elearning.features.enrollment.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder

public record EnrollmentResponse(
        Long id,
        LocalDate certifiedAt,
        String code,
        String courseId,
        String enrolledAt,
        boolean isCertified,
        boolean isDeleted,
        String studentId

) {

}
