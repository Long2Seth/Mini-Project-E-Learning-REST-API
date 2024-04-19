package co.istad.elearning.features.enrollment.dto;


import lombok.Builder;

@Builder
public record EnrollmentProgressResponse(
        String code,
        Long id,
        Integer progress
) {
}
