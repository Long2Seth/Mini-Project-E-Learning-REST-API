package co.istad.elearning.features.instuctor;

import co.istad.elearning.domain.Instructor;
import co.istad.elearning.features.instuctor.dto.InstructorCreateRequest;
import co.istad.elearning.features.instuctor.dto.InstructorResponse;
import co.istad.elearning.features.user.dto.UserCreateRequest;
import co.istad.elearning.features.user.dto.UserResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InstructorService {
    InstructorResponse createNew(UserCreateRequest userCreateRequest, InstructorCreateRequest instructorCreateRequest);

    Page<InstructorResponse> findList(int page, int limit);

    UserResponse findProfileByUsername(String username, HttpServletRequest request);

    String updateProfile(String username, String mediaName, HttpServletRequest request);
}
