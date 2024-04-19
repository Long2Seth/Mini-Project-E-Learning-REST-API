package co.istad.elearning.features.course;

import co.istad.elearning.base.BasedMessage;
import co.istad.elearning.features.course.dto.*;
import org.springframework.data.domain.Page;

public interface CourseService {
    Page<CourseResponse> getCourses(int page, int size);

    BasedMessage createCourse(CourseCreateRequest courseCreateRequest);

    CourseDetailsResponse findByAlias(String alias);


     BasedMessage updateThumbnail(CourseThumbnailRequest coursethumbnailRequest, String alias);

    BasedMessage updateCourseByAlias(String alias, CourseUpdateRequest courseUpdateRequest);
    BasedMessage updateCourseCategory(String alias,CourseCategoryRequest courseCategoryRequest);
    BasedMessage disableCourse(String alias);

    BasedMessage enableCourse(String alias);
}
