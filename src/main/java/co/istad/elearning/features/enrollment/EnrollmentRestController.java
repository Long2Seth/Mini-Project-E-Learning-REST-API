package co.istad.elearning.features.enrollment;


import co.istad.elearning.features.enrollment.dto.EnrollmentDetailResponse;
import co.istad.elearning.features.enrollment.dto.EnrollmentProgressRequest;
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
    public BaseResponse<Object> createEnrollment(@RequestBody EnrollmentRequest enrollmentRequest) {
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

    @PutMapping("/{code}/progress")
    @Operation(summary = "Update enrollment progress")
    public BaseResponse<Object> updateEnrollmentProgress(
            @PathVariable String code,
            @RequestBody EnrollmentProgressRequest enrollmentProgressRequest
    ) {
        return BaseResponse
                .ok()
                .setPayload(enrollmentService.updateEnrollmentProgress(code, enrollmentProgressRequest));
    }

    @GetMapping("/{code}/progress")
    @Operation(summary = "Get enrollment progress")
    public BaseResponse<EnrollmentDetailResponse> getEnrollmentProgress(@PathVariable String code) {
        return BaseResponse.<EnrollmentDetailResponse>ok()
                .setPayload(enrollmentService.getEnrollmentProgress(code));
    }

    @PutMapping("/{code}/is-certified")
    @Operation(summary = "Certify enrollment")
    public BaseResponse<Object> certifyEnrollment(@PathVariable String code) {
        return BaseResponse
                .ok()
                .setPayload(enrollmentService.certifyEnrollment(code));
    }
}
