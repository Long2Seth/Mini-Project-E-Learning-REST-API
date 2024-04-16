package co.istad.elearning.features.instuctor.dto;

import jakarta.validation.constraints.NotBlank;

public record InstructorUpdateRequest(
        @NotBlank(message = "Media is required")
        String mediaName
) {
}
