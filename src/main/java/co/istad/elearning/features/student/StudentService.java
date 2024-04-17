package co.istad.elearning.features.student;

import co.istad.elearning.features.student.dto.StudentCreateRequest;
import co.istad.elearning.features.student.dto.StudentResponse;
import co.istad.elearning.features.user.dto.UserCreateRequest;

public interface StudentService {
    StudentResponse createNew(UserCreateRequest userCreateRequest, StudentCreateRequest studentCreateRequest);
}
