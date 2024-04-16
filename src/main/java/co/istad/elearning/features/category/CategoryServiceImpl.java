package co.istad.elearning.features.category;


import co.istad.elearning.features.category.dto.CategoryRequest;
import co.istad.elearning.features.category.dto.CategoryResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements  CategoryService{
    @Override
    public List<CategoryResponse> getAllCategories() {
        return null;
    }

    @Override
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        return null;
    }

    @Override
    public CategoryResponse fineCategoryById(Integer id) {
        return null;
    }

    @Override
    public CategoryResponse updateCategory(Integer id, CategoryRequest categoryRequest) {
        return null;
    }

    @Override
    public void deleteCategory(Integer id) {

    }
}
