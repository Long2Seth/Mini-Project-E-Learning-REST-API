package co.istad.elearning.features.student;

import co.istad.elearning.features.instuctor.dto.InstructorResponse;
import co.istad.elearning.features.instuctor.dto.InstructorUpdateRequest;
import co.istad.elearning.features.student.dto.CombinedRequest;
import co.istad.elearning.features.student.dto.StudentResponse;
import co.istad.elearning.features.student.dto.StudentUpdateResquest;
import co.istad.elearning.features.user.dto.UserResponse;
import co.istad.elearning.util.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/students")
@Slf4j
public class StudentController {

    private final StudentService studentService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public BaseResponse<?> createNew(@Valid @RequestBody CombinedRequest combinedRequest){
        return BaseResponse.createSuccess()
                .setPayload(studentService.createNew(combinedRequest.userCreateRequest(),combinedRequest.studentCreateRequest()));

    }

    @GetMapping
    Page<StudentResponse> findList(@RequestParam(required = false, defaultValue = "0") int page,
                                   @RequestParam(required = false, defaultValue = "2") int limit){
        return studentService.findList(page, limit);
    }

    @GetMapping("/{username}")
    UserResponse findProfile(@PathVariable String username,HttpServletRequest request){
        return studentService.findProfileByUsername(username,request);
    }

    @PutMapping("/{username}")
    BaseResponse<?> updateProfileImage(@PathVariable String username,
                                       @Valid @RequestBody StudentUpdateResquest studentUpdateResquest,
                                       HttpServletRequest request) {

        return BaseResponse.updateSuccess()
                .setPayload(studentService.updateProfile(username, studentUpdateResquest.mediaName(),request));

    }

}
