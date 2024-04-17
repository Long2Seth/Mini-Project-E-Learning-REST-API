package co.istad.elearning.features.enrollment.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder

public record EnrollmentDetailResponse(
        Long id,
        LocalDate certifiedAt,
        Integer code,
        String courseId,
        String enrolledAt,
        boolean isCertified,
        boolean isDeleted,
        Integer progress,
        String studentId
) {
}