package co.istad.elearning.features.enrollment;


import co.istad.elearning.features.course.CourseRepository;
import co.istad.elearning.features.enrollment.dto.EnrollmentDetailResponse;
import co.istad.elearning.features.enrollment.dto.EnrollmentRequest;
import co.istad.elearning.features.enrollment.dto.EnrollmentResponse;
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

        var course = courseRepository.findById(Long.valueOf(enrollmentRequest.courseId())).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Course with ID: <" + enrollmentRequest.courseId() + "> " + "does not exist!"
                ));

        var enrollment = enrollmentMapper.requestToEnrollment(enrollmentRequest);
        enrollment.setCourse(course);
        enrollment.setStudent(student);

        enrollmentRepository.save(enrollment);
        var enrollmentResponse = enrollmentMapper.responseToEnrollmentResponse(enrollment);
        return enrollmentResponse;


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
        var enrollment = enrollmentRepository.findByCode(Integer.valueOf(code))
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.BAD_REQUEST,
                                "Enrollment with code: <" + code + "> " + "does not exist!"
                        )
                );

        return enrollmentMapper.responseToEnrollmentResponse(enrollment);
    }

    @Override
    public EnrollmentResponse updateEnrollment(EnrollmentRequest enrollmentRequest) {
        return null;
    }

    @Override
    public EnrollmentResponse disableEnrollment(Integer code) {
        return null;
    }
}
