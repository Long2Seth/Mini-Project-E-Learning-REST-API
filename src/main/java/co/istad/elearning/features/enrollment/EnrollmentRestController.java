package co.istad.elearning.features.enrollment;


import co.istad.elearning.features.enrollment.dto.EnrollmentResponse;
import co.istad.elearning.util.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static co.istad.elearning.util.BaseResponse.enrolledSuccess;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/enrollments")
public class EnrollmentRestController {

    private final EnrollmentService enrollmentService;

    @PostMapping
    @Operation(summary = "Create new enrollment")
    public BaseResponse<EnrollmentResponse> createEnrollment(@RequestBody EnrollmentResponse enrollmentResponse) {
        return null;
    }

    @GetMapping
    @Operation(summary = "Get all enrollments")
    public BaseResponse<EnrollmentResponse> getEnrollments() {
        return null;
    }

    @GetMapping("/{code}")
    @Operation(summary = "Get enrollment by code")
    public BaseResponse<EnrollmentResponse> getEnrollmentByCode(@PathVariable Integer code) {
        return null;
    }

    @PutMapping
    @Operation(summary = "Disable enrollment by code")
    public BaseResponse<EnrollmentResponse> disableEnrollment(@RequestBody EnrollmentResponse enrollmentResponse) {
        return null;
    }
}
