package co.istad.elearning.mapper;

import co.istad.elearning.domain.Enrollment;
import co.istad.elearning.features.enrollment.dto.EnrollmentDetailResponse;
import co.istad.elearning.features.enrollment.dto.EnrollmentProgressResponse;
import co.istad.elearning.features.enrollment.dto.EnrollmentRequest;
import co.istad.elearning.features.enrollment.dto.EnrollmentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface EnrollmentMapper {

    @Mapping(target = "id",ignore = true)
    Enrollment requestToEnrollment(EnrollmentRequest enrollmentRequest);

    @Mapping(target = "studentId", source = "student.id")
    @Mapping(target = "courseId", source = "course.id")
    EnrollmentDetailResponse responseToEnrollmentResponse(Enrollment enrollmentResponse);

    EnrollmentProgressResponse responseToEnrollmentProgress(Enrollment enrollmentResponse);
}
