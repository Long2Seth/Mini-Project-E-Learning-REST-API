package co.istad.elearning.features.category;

import co.istad.elearning.features.category.dto.CategoryRequest;
import co.istad.elearning.features.category.dto.CategoryResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> getAllCategories();
    CategoryResponse createCategory(CategoryRequest categoryRequest);
    CategoryResponse findCategoryById(Long id);
    CategoryResponse updateCategory(Integer id, CategoryRequest categoryRequest);
    void deleteCategory(Integer id);

}
