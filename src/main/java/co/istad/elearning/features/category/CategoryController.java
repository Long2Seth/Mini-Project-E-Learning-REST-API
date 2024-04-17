package co.istad.elearning.features.category;


import co.istad.elearning.features.category.dto.CategoryRequest;
import co.istad.elearning.features.category.dto.CategoryResponse;
import co.istad.elearning.util.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/category")
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<List<CategoryResponse>> getAllCategories() {
        return BaseResponse.<List<CategoryResponse>>ok()
                .setPayload(categoryService.getAllCategories());
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<CategoryResponse> createCategory(@RequestBody CategoryRequest categoryRequest) {
        return BaseResponse.<CategoryResponse>ok()
                .setPayload(categoryService.createCategory(categoryRequest));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<CategoryResponse> getCategoryById(@PathVariable Long id) {
        return BaseResponse.<CategoryResponse>ok()
                .setPayload(categoryService.findCategoryById(id));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<CategoryResponse> updateCategory(@PathVariable Long id, @RequestBody CategoryRequest categoryRequest) {
        return BaseResponse.<CategoryResponse>ok()
                .setPayload(categoryService.updateCategory(id, categoryRequest));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }

    @PutMapping("/{id}/enable")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<CategoryResponse> enableCategory(@PathVariable Long id) {
        return BaseResponse.<CategoryResponse>ok()
                .setPayload(categoryService.enableCategory(id));
    }

    @PutMapping("/{id}/disable")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<CategoryResponse> disableCategory(@PathVariable Long id) {
        return BaseResponse.<CategoryResponse>ok()
                .setPayload(categoryService.disableCategory(id));
    }
}
