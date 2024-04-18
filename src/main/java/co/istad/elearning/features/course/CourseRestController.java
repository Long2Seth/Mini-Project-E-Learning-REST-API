package co.istad.elearning.features.course;

import co.istad.elearning.base.BaseResponse;
import co.istad.elearning.features.course.dto.*;
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
    Page<CourseResponse> findAllCourse(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "25") int size
    ) {
        return courseService.findList(page, size);
    }

    @GetMapping("/{alias}")
    @Operation(summary = "Find a course details")
    @ResponseStatus(HttpStatus.OK)
    CourseDetailsResponse findCourseByAlias(@PathVariable("alias") String alias) {
        return courseService.findByAlias(alias);
    }


    @PutMapping("{alias}")
    @Operation(summary = "Update a course")
    @ResponseStatus(HttpStatus.OK)
    public CourseResponse updateCourseByAlias(@PathVariable("alias") String alias,
                                              @RequestBody CourseUpdateRequest courseUpdateRequest) {
        return courseService.updateCourseByAlias(alias, courseUpdateRequest);
    }

    @PutMapping("/{alias}/thumbnail")
    @Operation(summary = "Update a course’s thumbnail")
    @ResponseStatus(HttpStatus.OK)
    public CourseResponse updateCourseThumbnailByAlias(@PathVariable("alias") String alias,
                                                       @RequestBody CourseThumbnailRequest courseThumbnailRequest) {
        return courseService.updateCourseThumbnailByAlias(alias, courseThumbnailRequest);
    }

    @PutMapping("/{alias}/categories")
    @Operation(summary = "Update a course’s categories")
    @ResponseStatus(HttpStatus.OK)
    public CourseResponse updateCourseCategoryByAlias(@PathVariable("alias") String alias,
                                                       @RequestBody CourseCategoryRequest courseCategoryRequest) {
        return courseService.updateCourseCategoryByAlias(alias, courseCategoryRequest);
    }

    @PutMapping("{alias}/disable")
    @Operation(summary = "Update a course’s categories")
    @ResponseStatus(HttpStatus.OK)
    public void disableCourseByAlias(@PathVariable("alias") String alias){
        courseService.disableCourseByAlias(alias);
    }

}
