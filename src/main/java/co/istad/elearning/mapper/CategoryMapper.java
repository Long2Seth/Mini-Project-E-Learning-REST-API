package co.istad.elearning.mapper;

import co.istad.elearning.domain.Category;
import co.istad.elearning.features.category.dto.CategoryRequest;
import co.istad.elearning.features.category.dto.CategoryResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category requestToCategory(CategoryRequest request) ;
    default CategoryResponse categoryToResponse(Category category) {
        if (category == null) {
            return null;
        }

        // Extract parentId from parentCategory field if it exists
        Integer parentId = (category.getParentCategory() != null) ? Math.toIntExact(category.getParentCategory().getId()) : null;

        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getAlias(),
                category.getIcon(),
                parentId,
                category.getIsDeleted()
        );
    }
}
