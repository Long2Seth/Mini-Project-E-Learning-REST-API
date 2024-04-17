package co.istad.elearning.mapper;

import co.istad.elearning.features.enrollment.dto.EnrollmentResponse;
import org.mapstruct.Mapping;

public interface EnrollmentMapper {

    @Mapping(target = "id", source = "id")
    EnrollmentResponse responseToEnrollmentResponse(EnrollmentResponse enrollmentResponse);
}
