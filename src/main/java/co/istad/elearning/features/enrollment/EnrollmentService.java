package co.istad.elearning.features.enrollment;

import co.istad.elearning.features.enrollment.dto.EnrollmentDetailResponse;
import co.istad.elearning.features.enrollment.dto.EnrollmentResponse;

import java.util.List;

public interface EnrollmentService {
    EnrollmentResponse createEnrollment(EnrollmentResponse enrollmentResponse);
    List<EnrollmentResponse> getEnrollments();
    EnrollmentDetailResponse getEnrollmentByCode(Integer code);
    EnrollmentResponse updateEnrollment(EnrollmentResponse enrollmentResponse);
    EnrollmentResponse disableEnrollment(Integer code);
}
