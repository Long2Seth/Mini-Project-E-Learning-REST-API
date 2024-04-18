package co.istad.elearning.features.enrollment;

import co.istad.elearning.features.enrollment.dto.EnrollmentDetailResponse;
import co.istad.elearning.features.enrollment.dto.EnrollmentRequest;
import co.istad.elearning.features.enrollment.dto.EnrollmentResponse;

import java.util.List;

public interface EnrollmentService {
    EnrollmentDetailResponse createEnrollment(EnrollmentRequest enrollmentRequest);
    List<EnrollmentDetailResponse> getEnrollments();
    EnrollmentDetailResponse getEnrollmentByCode(String code);
    EnrollmentResponse updateEnrollment(EnrollmentRequest enrollmentRequest);
    EnrollmentResponse disableEnrollment(Integer code);
}
