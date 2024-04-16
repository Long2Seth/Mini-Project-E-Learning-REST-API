package co.istad.elearning.features.instuctor;

import co.istad.elearning.features.instuctor.dto.CombinedRequest;
import co.istad.elearning.features.instuctor.dto.InstructorCreateRequest;
import co.istad.elearning.features.instuctor.dto.InstructorResponse;
import co.istad.elearning.features.user.dto.UserCreateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/instructors")
@Slf4j
public class InstructorController {

    private final InstructorService instructorService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createNew(@Valid @RequestBody CombinedRequest combinedRequest){
        try {
            instructorService.createNew(combinedRequest.userCreateRequest(),combinedRequest.instructorCreateRequest());
        } catch (Exception e) {
            log.error("An error occurred: {}", e.getMessage(), e);
        }
    }

    @GetMapping
    Page<InstructorResponse> findlist(@RequestParam(required = false, defaultValue = "0") int page,
                                      @RequestParam(required = false, defaultValue = "2") int limit){
        return instructorService.findList(page, limit);
    }




}
