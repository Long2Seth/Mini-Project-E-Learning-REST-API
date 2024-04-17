package co.istad.elearning.features.instuctor;

import co.istad.elearning.features.instuctor.dto.CombinedRequest;
import co.istad.elearning.features.instuctor.dto.InstructorResponse;
import co.istad.elearning.features.instuctor.dto.InstructorUpdateRequest;
import co.istad.elearning.features.user.dto.UserResponse;
import co.istad.elearning.util.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/instructors")
@Slf4j
public class InstructorController {

    private final InstructorService instructorService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public BaseResponse<?> createNew(@Valid @RequestBody CombinedRequest combinedRequest){
            return BaseResponse.createSuccess()
                    .setPayload(instructorService.createNew(combinedRequest.userCreateRequest(),combinedRequest.instructorCreateRequest()));

    }

    @GetMapping
    Page<InstructorResponse> findList(@RequestParam(required = false, defaultValue = "0") int page,
                                      @RequestParam(required = false, defaultValue = "2") int limit){
        return instructorService.findList(page, limit);
    }


    @GetMapping("/{username}")
    UserResponse findProfile(@PathVariable String username,HttpServletRequest request){
        return instructorService.findProfileByUsername(username,request);
    }

    @PutMapping("/{username}")
    BaseResponse<?> updateProfileImage(@PathVariable String username,
                                        @Valid @RequestBody InstructorUpdateRequest instructorUpdateRequest,
                                       HttpServletRequest request) {

        return BaseResponse.updateSuccess()
                .setPayload(instructorService.updateProfile(username, instructorUpdateRequest.mediaName(),request));

    }




}
