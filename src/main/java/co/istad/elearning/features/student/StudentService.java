package co.istad.elearning.features.student;

import co.istad.elearning.features.instuctor.dto.InstructorResponse;
import co.istad.elearning.features.student.dto.StudentCreateRequest;
import co.istad.elearning.features.student.dto.StudentResponse;
import co.istad.elearning.features.user.dto.UserCreateRequest;
import co.istad.elearning.features.user.dto.UserDetailResponse;
import co.istad.elearning.features.user.dto.UserResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;

public interface StudentService {

    StudentResponse createNew(UserCreateRequest userCreateRequest, StudentCreateRequest studentCreateRequest);

    Page<StudentResponse> findList(int page, int limit);

    UserResponse findProfileByUsername(String username, HttpServletRequest request);

    String updateProfile(String username, String mediaName, HttpServletRequest request);

}
