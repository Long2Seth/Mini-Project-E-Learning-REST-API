package co.istad.elearning.features.category.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record CategoryResponse(
        Long id,
        String name,
        String alias,
        String icon,
        Integer parentCategoryId,
        Boolean isDeleted,
        List<CategoryParentResponse> parent
) {
}
