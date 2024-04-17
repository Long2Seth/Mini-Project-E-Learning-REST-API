package co.istad.elearning.features.student.dto;

import jakarta.validation.constraints.NotBlank;

public record StudentUpdateResquest(
        @NotBlank(message = "Media is required")
        String mediaName
) {
}
