package co.istad.elearning.mapper;

import co.istad.elearning.domain.Category;
import co.istad.elearning.features.category.dto.CategoryParentResponse;
import co.istad.elearning.features.category.dto.CategoryRequest;
import co.istad.elearning.features.category.dto.CategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "parent", source = "category.parentCategory", qualifiedByName = "mapParentCategory")
    CategoryResponse categoryToResponse(Category category);

    @Named("mapParentCategory")
    default List<CategoryParentResponse> mapParentCategory(Category parentCategory) {
        if (parentCategory == null) {
            return null;
        }

        // Map the parent category to CategoryParentResponse
        return List.of(CategoryParentResponse.builder()
                .name(parentCategory.getName())
                .alias(parentCategory.getAlias())
                .icon(parentCategory.getIcon())
                .isDeleted(parentCategory.getIsDeleted())
                .build());
    }

    Category requestToCategory(CategoryRequest request);

    void updateCategoryFromRequest(@MappingTarget Category category, CategoryRequest request);
}
