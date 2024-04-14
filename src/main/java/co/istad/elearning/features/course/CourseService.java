package co.istad.elearning.features.course;

import co.istad.elearning.features.course.dto.CourseCreateRequest;
import co.istad.elearning.features.course.dto.CourseDetailsResponse;
import co.istad.elearning.features.course.dto.CourseResponse;
import org.springframework.data.domain.Page;

public interface CourseService {
    Page<CourseResponse> findList(int page, int size);
    void createNew(CourseCreateRequest courseCreateRequest);
    CourseDetailsResponse findByAlias(String alias);
}
