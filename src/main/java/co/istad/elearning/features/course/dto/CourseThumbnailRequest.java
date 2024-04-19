package co.istad.elearning.features.course.dto;

import jakarta.validation.constraints.NotBlank;

public record CourseThumbnailRequest(
        @NotBlank(message = "Course thumbnail is require")
        String thumbnail
) {
}