package co.istad.elearning.features.enrollment.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;


@Builder
public record EnrollmentRequest(
        @NotEmpty
        String code,
        @NotEmpty
        Long courseId,
        @NotEmpty
        Long studentId
) {
}
