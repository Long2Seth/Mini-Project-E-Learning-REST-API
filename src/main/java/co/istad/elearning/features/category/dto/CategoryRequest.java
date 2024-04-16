package co.istad.elearning.features.category.dto;

public record CategoryRequest(
        String alias,
        String icon,
        Boolean isDeleted,
        String name,
        Integer parentCategoryId
) {
}
