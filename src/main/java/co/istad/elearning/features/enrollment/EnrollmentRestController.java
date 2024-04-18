package co.istad.elearning.features.enrollment;


import co.istad.elearning.features.enrollment.dto.EnrollmentDetailResponse;
import co.istad.elearning.features.enrollment.dto.EnrollmentRequest;
import co.istad.elearning.util.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/enrollments")
public class EnrollmentRestController {

    private final EnrollmentService enrollmentService;

    @PostMapping
    @Operation(summary = "Create new enrollment")
    public BaseResponse<Object> createEnrollment(@RequestBody EnrollmentRequest enrollmentRequest){
        return BaseResponse.ok().setPayload(enrollmentService.createEnrollment(enrollmentRequest));
    }

    @GetMapping
    @Operation(summary = "Get all enrollments")
    public BaseResponse<List<EnrollmentDetailResponse>> getEnrollments() {
        return BaseResponse.<List<EnrollmentDetailResponse>>ok()
                .setPayload(enrollmentService.getEnrollments());
    }

    @GetMapping("/{code}")
    @Operation(summary = "Get enrollment by code")
    public BaseResponse<EnrollmentDetailResponse> getEnrollmentByCode(@PathVariable String code) {
        return BaseResponse.<EnrollmentDetailResponse>ok()
                .setPayload(enrollmentService.getEnrollmentByCode(code));
    }

    @PutMapping("/{code}")
    @Operation(summary = "Disable enrollment by code")
    public BaseResponse<Object> disableEnrollment(@PathVariable String code) {
        return BaseResponse
                .disableSuccess()
                .setPayload(enrollmentService.disableEnrollment(code));
    }
}
