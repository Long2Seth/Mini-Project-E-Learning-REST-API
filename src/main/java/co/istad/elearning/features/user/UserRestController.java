package co.istad.elearning.features.user;

import co.istad.elearning.base.BaseResponse;
import co.istad.elearning.features.user.dto.UserDetailResponse;
import co.istad.elearning.features.user.dto.UserResponse;
import co.istad.elearning.mapper.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor

public class UserRestController {
    private final UserService userService;

    @PutMapping("/{username}/disable")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Disable user")
    public BaseResponse<UserDetailResponse> disableUser(@PathVariable String username) {
        return BaseResponse.<UserDetailResponse>updateSuccess()
                .setPayload(userService.disableUser(username));
    }

    @PutMapping("/{username}/enable")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Enable user")
    public BaseResponse<UserDetailResponse> enableUser(@PathVariable String username) {
        return BaseResponse.<UserDetailResponse>updateSuccess()
                .setPayload(userService.enableUser(username));
    }

    @DeleteMapping("/{username}")
    @Operation(summary = "Delete by username")
    public BaseResponse<UserDetailResponse> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return BaseResponse.deleteSuccess();
    }



}
 