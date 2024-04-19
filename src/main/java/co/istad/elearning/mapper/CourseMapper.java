package co.istad.elearning.mapper;

import co.istad.elearning.domain.Course;
import co.istad.elearning.features.course.dto.CourseCreateRequest;
import co.istad.elearning.features.course.dto.CourseDetailsResponse;
import co.istad.elearning.features.course.dto.CourseResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    @Mapping(source = "categoryId", target = "category")
    @Mapping(source = "instructorId", target = "instructor")
    Course fromCourseCreateRequest(CourseCreateRequest courseCreateRequest);
    CourseDetailsResponse toCourseDetailResponse(Course course);
    CourseResponse toCourseResponse(Course course);

}
