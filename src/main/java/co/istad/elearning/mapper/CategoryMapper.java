package co.istad.elearning.mapper;

import co.istad.elearning.domain.Category;
import co.istad.elearning.features.category.dto.CategoryRequest;
import co.istad.elearning.features.category.dto.CategoryResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category requestToCategory(CategoryRequest request) ;
    default CategoryResponse categoryToResponse(Category category) {
        if (category == null) {
            return null;
        }

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


    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
    void updateCategoryFromRequest(@MappingTarget Category category , CategoryRequest request);
}
