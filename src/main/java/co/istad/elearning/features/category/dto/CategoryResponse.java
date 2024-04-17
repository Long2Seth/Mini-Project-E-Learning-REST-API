package co.istad.elearning.features.category.dto;

import lombok.Builder;

@Builder
public record CategoryResponse(
        Long id,
        String name,
        String alias,
        String icon,
        Integer parentId,
        Boolean isDeleted
) {
}
