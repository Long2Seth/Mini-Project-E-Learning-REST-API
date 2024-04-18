package co.istad.elearning.features.category.dto;


import lombok.Builder;

@Builder
public record CategoryRequest(
        String alias,
        String icon,
        Boolean isDeleted,
        String name,
        Long parentCategoryId
) {}
