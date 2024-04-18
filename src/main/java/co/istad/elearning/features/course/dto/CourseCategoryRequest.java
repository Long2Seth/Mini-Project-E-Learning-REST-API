package co.istad.elearning.features.course.dto;

import co.istad.elearning.domain.Category;
import jakarta.validation.constraints.NotNull;

public record CourseCategoryRequest(
        @NotNull(message = "Category ID is required")
        Category category
) {
}