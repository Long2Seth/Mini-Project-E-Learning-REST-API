package co.istad.elearning.features.enrollment;

import co.istad.elearning.features.enrollment.dto.EnrollmentDetailResponse;
import co.istad.elearning.features.enrollment.dto.EnrollmentProgressRequest;
import co.istad.elearning.features.enrollment.dto.EnrollmentProgressResponse;
import co.istad.elearning.features.enrollment.dto.EnrollmentRequest;

import java.util.List;

public interface EnrollmentService {
    EnrollmentDetailResponse createEnrollment(EnrollmentRequest enrollmentRequest);
    List<EnrollmentDetailResponse> getEnrollments();
    EnrollmentDetailResponse getEnrollmentByCode(String code);
    EnrollmentProgressResponse updateEnrollmentProgress(String code , EnrollmentProgressRequest enrollmentProgressRequest);
    EnrollmentDetailResponse getEnrollmentProgress(String code);

    EnrollmentDetailResponse certifyEnrollment(String code);
    EnrollmentDetailResponse disableEnrollment(String code);
}
