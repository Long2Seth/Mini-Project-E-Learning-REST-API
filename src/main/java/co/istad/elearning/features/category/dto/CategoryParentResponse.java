package co.istad.elearning.features.category.dto;

import lombok.Builder;

@Builder
public record CategoryParentResponse(
        String name,
        String alias,
        String icon,
        Boolean isDeleted
) {
}
