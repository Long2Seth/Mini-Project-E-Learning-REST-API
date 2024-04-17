package co.istad.elearning.features.course;

import co.istad.elearning.features.course.dto.*;
import org.springframework.data.domain.Page;

public interface CourseService {
    Page<CourseResponse> findList(int page, int size);

    void createNew(CourseCreateRequest courseCreateRequest);

    CourseDetailsResponse findByAlias(String alias);

    CourseResponse updateCourseByAlias(String alias, CourseUpdateRequest courseUpdateRequest);

    CourseResponse updateCourseThumbnailByAlias(String alias, CourseThumbnailRequest courseThumbnailRequest);

    CourseResponse updateCourseCategoryByAlias(String alias, CourseCategoryRequest courseCategoryRequest);
    void disableCourseByAlias(String alias);
}
