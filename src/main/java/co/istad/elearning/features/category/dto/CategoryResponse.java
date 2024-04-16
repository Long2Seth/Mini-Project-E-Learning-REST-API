package co.istad.elearning.features.category.dto;

public record CategoryResponse(
        Integer id,
        String name,
        String alias,
        String icon,
        Integer parentId,
        Boolean isDeleted
) {
}
