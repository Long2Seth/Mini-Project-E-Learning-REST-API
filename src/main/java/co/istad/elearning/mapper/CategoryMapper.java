package co.istad.elearning.mapper;

import co.istad.elearning.domain.Category;
import co.istad.elearning.features.category.dto.CategoryParentResponse;
import co.istad.elearning.features.category.dto.CategoryRequest;
import co.istad.elearning.features.category.dto.CategoryResponse;
import org.mapstruct.*;

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
        return List.of(CategoryParentResponse.builder()
                .name(parentCategory.getName())
                .alias(parentCategory.getAlias())
                .icon(parentCategory.getIcon())
                .isDeleted(parentCategory.getIsDeleted())
                .build());
    }

    Category requestToCategory(CategoryRequest request);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
    void updateCategoryFromRequest(@MappingTarget Category category, CategoryRequest request);
}
