package co.istad.elearning.features.category.dto;


import lombok.Builder;

@Builder
public record CategoryRequest(
        String name,
        String alias,
        String icon,
        Boolean isDeleted,
        Integer parentCategoryId
) {}
