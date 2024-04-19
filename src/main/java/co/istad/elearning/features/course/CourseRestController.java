package co.istad.elearning.features.course;

import co.istad.elearning.base.BaseResponse;
import co.istad.elearning.base.BasedMessage;
import co.istad.elearning.features.course.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/courses")
@RequiredArgsConstructor
@Slf4j
public class CourseRestController {
    private final CourseService courseService;

    @PostMapping()
    @Operation(summary = "Create new course")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAccount(@Valid @RequestBody CourseCreateRequest request) {
        courseService.createCourse(request);
    }

    @GetMapping
    @Operation(summary = "Find all courses by pagination")
    @ResponseStatus(HttpStatus.OK)
    Page<CourseResponse> findAllCourse(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "25") int size
    ) {
        return courseService.getCourses(page, size);
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
    public BasedMessage updateCourseByAlias(@PathVariable("alias") String alias,
                                              @RequestBody CourseUpdateRequest courseUpdateRequest) {
        return courseService.updateCourseByAlias(alias, courseUpdateRequest);
    }

    @PutMapping("/{alias}/thumbnail")
    @Operation(summary = "Update a course’s thumbnail")
    @ResponseStatus(HttpStatus.OK)
    public BasedMessage updateCourseThumbnailByAlias(@PathVariable("alias") String alias,
                                                     @RequestBody CourseThumbnailRequest courseThumbnailRequest) {
        return courseService.updateThumbnail( courseThumbnailRequest,alias);
    }

    @PutMapping("/{alias}/categories")
    @Operation(summary = "Update a course’s categories")
    @ResponseStatus(HttpStatus.OK)
    public BasedMessage updateCourseCategoryByAlias(@PathVariable("alias") String alias,
                                                       @RequestBody CourseCategoryRequest courseCategoryRequest) {
        return courseService.updateCourseCategory(alias, courseCategoryRequest);
    }

    @PutMapping("{alias}/disable")
    @Operation(summary = "Update a course’s categories")
    @ResponseStatus(HttpStatus.OK)
    public void disableCourseByAlias(@PathVariable("alias") String alias){
        courseService.disableCourse(alias);
    }

}
