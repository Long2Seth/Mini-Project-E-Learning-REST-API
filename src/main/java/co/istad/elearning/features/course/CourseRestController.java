package co.istad.elearning.features.course;

import co.istad.elearning.base.BaseResponse;
import co.istad.elearning.features.course.dto.CourseCreateRequest;
import co.istad.elearning.features.course.dto.CourseDetailsResponse;
import co.istad.elearning.features.course.dto.CourseResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/courses")
@RequiredArgsConstructor
public class CourseRestController {
    private final CourseService courseService;


    @PostMapping()
    @Operation(summary = "Create new course")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAccount(@Valid @RequestBody CourseCreateRequest request) {
        courseService.createNew(request);
    }

    @GetMapping
    @Operation(summary = "Find all courses by pagination")
    @ResponseStatus(HttpStatus.OK)
    Page<CourseResponse> findList(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "25") int size
    ) {
        return courseService.findList(page, size);
    }

    @GetMapping("/{alias}")
    @Operation(summary = "Find a course details")
    @ResponseStatus(HttpStatus.OK)
    CourseDetailsResponse findByAlias(@PathVariable("alias") String alias) {
        return courseService.findByAlias(alias);
    }
}
