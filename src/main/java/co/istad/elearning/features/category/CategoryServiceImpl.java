package co.istad.elearning.features.category;


import co.istad.elearning.domain.Category;
import co.istad.elearning.features.category.dto.CategoryParentResponse;
import co.istad.elearning.features.category.dto.CategoryRequest;
import co.istad.elearning.features.category.dto.CategoryResponse;
import co.istad.elearning.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements  CategoryService{

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    @Override
    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll().stream().map(categoryMapper::categoryToResponse).toList();
    }

    @Override
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {


        Category newCategory = categoryMapper.requestToCategory(categoryRequest);
        newCategory.setIsDeleted(false);
        newCategory.setName(categoryRequest.name());
        newCategory.setAlias(categoryRequest.alias());
        newCategory.setIcon(categoryRequest.icon());
        categoryRepository.save(newCategory);
        return categoryMapper.categoryToResponse(newCategory);
    }

    @Override
    public CategoryResponse findCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        return categoryMapper.categoryToResponse(category);
    }

    @Override
    public CategoryResponse updateCategory(Long id, CategoryRequest categoryRequest) {
        var category = categoryRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Category not found with id: " + id));
        categoryMapper.updateCategoryFromRequest( category , categoryRequest);
        return categoryMapper.categoryToResponse(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(Long id) {
        var deleteId = categoryRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Category not found with id: " + id));
        categoryRepository.delete(deleteId);
    }

    @Override
    public CategoryResponse enableCategory(Long id) {
        int row = categoryRepository.updateBlockedStatusById(id, false);
        if(row > 0 ){
            return categoryMapper.categoryToResponse(categoryRepository.findById(id).orElse(null));
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found with id: " + id);
        }
    }

    @Override
    public CategoryResponse disableCategory(Long id) {
        int row = categoryRepository.updateBlockedStatusById(id, true);
        if(row > 0 ){
            return categoryMapper.categoryToResponse(categoryRepository.findById(id).orElse(null));
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found with id: " + id);
        }
    }
}
