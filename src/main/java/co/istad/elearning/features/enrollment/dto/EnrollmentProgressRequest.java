package co.istad.elearning.features.enrollment.dto;

import lombok.Builder;

@Builder
public record EnrollmentProgressRequest(
        Integer progress
) {
}
