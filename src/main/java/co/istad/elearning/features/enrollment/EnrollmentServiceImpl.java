package co.istad.elearning.features.enrollment;


import co.istad.elearning.domain.Course;
import co.istad.elearning.features.enrollment.dto.EnrollmentDetailResponse;
import co.istad.elearning.features.enrollment.dto.EnrollmentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService{

    private final EnrollmentRepository enrollmentRepository;
    @Override
    public EnrollmentResponse createEnrollment(EnrollmentResponse enrollmentResponse) {
        return null;
    }

    @Override
    public List<EnrollmentResponse> getEnrollments() {
        return null;
    }

    @Override
    public EnrollmentDetailResponse getEnrollmentByCode(Integer code) {
        return null;
    }

    @Override
    public EnrollmentResponse updateEnrollment(EnrollmentResponse enrollmentResponse) {
        return null;
    }

    @Override
    public EnrollmentResponse disableEnrollment(Integer code) {
        return null;
    }
}
