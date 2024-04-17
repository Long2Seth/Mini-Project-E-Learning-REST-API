package co.istad.elearning.features.category.dto;

import lombok.Builder;

@Builder
public record CategoryParentResponse(
        Long id,
        String name,
        String alias,
        String icon,
        Integer subcategoriesCount,
        Boolean isDeleted
) {
}
