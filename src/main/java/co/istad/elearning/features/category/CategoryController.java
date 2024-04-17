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

    @GetMapping
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

}
