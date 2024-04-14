package co.istad.elearning.mapper;

import co.istad.elearning.domain.Course;
import co.istad.elearning.features.course.dto.CourseResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
})
public interface CourseMapper {
    @Mapping(source = "", target = "course",
            qualifiedByName = "mapCourseResponse")
    CourseResponse toCourseResponse(Course course);
}
