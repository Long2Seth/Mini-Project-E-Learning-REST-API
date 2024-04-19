package co.istad.elearning.features.enrollment;


import co.istad.elearning.features.course.CourseRepository;
import co.istad.elearning.features.enrollment.dto.EnrollmentDetailResponse;
import co.istad.elearning.features.enrollment.dto.EnrollmentProgressRequest;
import co.istad.elearning.features.enrollment.dto.EnrollmentProgressResponse;
import co.istad.elearning.features.enrollment.dto.EnrollmentRequest;
import co.istad.elearning.features.student.StudentRepository;
import co.istad.elearning.mapper.EnrollmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final EnrollmentMapper enrollmentMapper;

    @Override
    public EnrollmentDetailResponse createEnrollment(EnrollmentRequest enrollmentRequest) {
        var student = studentRepository.findById(Math.toIntExact(enrollmentRequest.studentId())).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Student with ID: <" + enrollmentRequest.studentId() + "> " + "does not exist!"
                ));

        var course = courseRepository.findById(enrollmentRequest.courseId()).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Course with ID: <" + enrollmentRequest.courseId() + "> " + "does not exist!"
                ));

        var enrollment = enrollmentMapper.requestToEnrollment(enrollmentRequest);
        enrollment.setCourse(course);
        enrollment.setStudent(student);

        enrollmentRepository.save(enrollment);
        return enrollmentMapper.responseToEnrollmentResponse(enrollment);
    }

    @Override
    public List<EnrollmentDetailResponse> getEnrollments() {
        return enrollmentRepository.findAll()
                .stream()
                .map(enrollmentMapper::responseToEnrollmentResponse)
                .toList();
    }

    @Override
    public EnrollmentDetailResponse getEnrollmentByCode(String code) {
        var enrollment = enrollmentRepository.findByCode(code)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.BAD_REQUEST,
                                "Enrollment with code: <" + code + "> " + "does not exist!"
                        )
                );

        return enrollmentMapper.responseToEnrollmentResponse(enrollment);
    }

    @Override
    public EnrollmentProgressResponse updateEnrollmentProgress(
            String code,
            EnrollmentProgressRequest enrollmentProgressRequest
    ) {
        var enrollment = enrollmentRepository.findByCode(code)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.BAD_REQUEST,
                                "Enrollment with code: <" + code + "> " + "does not exist!"
                        )
                );

        enrollment.setProgress(enrollmentProgressRequest.progress());
        enrollmentRepository.save(enrollment);

        return enrollmentMapper.responseToEnrollmentProgress(enrollment);
    }

    @Override
    public EnrollmentDetailResponse getEnrollmentProgress(String code) {
        return null;
    }

    @Override
    public EnrollmentDetailResponse certifyEnrollment(String code) {
        //Certify an enrollment when progress 100
        var enrollment = enrollmentRepository.findByCode(code)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.BAD_REQUEST,
                                "Enrollment with code: <" + code + "> " + "does not exist!"
                        )
                );
        if (enrollment.getProgress() >= 100) {
            enrollment.setIsCertified(true);
            enrollmentRepository.save(enrollment);
            return enrollmentMapper.responseToEnrollmentResponse(enrollment);
        } else {
            enrollment.setIsCertified(false);
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Enrollment with code: <" + code + "> " + "is not completed yet!"
            );
        }
    }


    @Override
    public EnrollmentDetailResponse disableEnrollment(String code) {
        int affectedRow = enrollmentRepository.disableByCode(code, true);
        if (affectedRow > 0) {
            return enrollmentMapper.responseToEnrollmentResponse(
                    enrollmentRepository.findByCode(code).orElse(null)
            );
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "User with code: <" + code + "> not found!"
            );

        }
    }
}
