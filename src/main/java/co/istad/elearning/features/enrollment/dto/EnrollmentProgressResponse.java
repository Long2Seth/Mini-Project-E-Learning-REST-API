package co.istad.elearning.features.enrollment.dto;


import lombok.Builder;

@Builder
public record EnrollmentProgressResponse(
        Long id,
        Integer progress
) {
}
