package co.istad.elearning.features.student;

import co.istad.elearning.features.student.dto.CombinedRequest;
import co.istad.elearning.util.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
//        return BaseResponse.createSuccess()
//                .setPayload(studentService.createNew(combinedRequest.userCreateRequest(),combinedRequest.studentCreateRequest()));

        try {
            return BaseResponse.createSuccess()
                    .setPayload(studentService.createNew(combinedRequest.userCreateRequest(),combinedRequest.studentCreateRequest()));
        } catch (Exception e) {
            log.error("Error creating new student", e);
            // Optionally, you can return a different response or rethrow the exception
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating new student", e);
        }

    }

}
