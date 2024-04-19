package co.istad.elearning.features.user;

import co.istad.elearning.features.user.dto.UserDetailResponse;
import co.istad.elearning.features.user.dto.UserGetResponse;
import co.istad.elearning.features.user.dto.UserResponse;
import co.istad.elearning.util.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/")
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/roles")
    public BaseResponse<?> findAllRoles() {
        return BaseResponse.ok()
                .setPayload(userService.findAllRoles());
    }


    @GetMapping("/roles/{name}")
    public BaseResponse<?> findRoleByName(@PathVariable String name) {
        return BaseResponse.ok()
                .setPayload(userService.findRoleByRoleName(name));
    }

    @GetMapping("/users")
    public BaseResponse<List<UserGetResponse>> getAllUsers() {
        return BaseResponse.<List<UserGetResponse>>ok()
                .setPayload(userService.getAllUsers());
    }

    @GetMapping("/users/{username}")
    public BaseResponse<UserGetResponse> findUserByUsername(@PathVariable String username) {
        return BaseResponse.<UserGetResponse>ok()
                .setPayload(userService.findUserByUsername(username));
    }

    @DeleteMapping("/users/{username}")
    public BaseResponse<UserGetResponse> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return BaseResponse.<UserGetResponse>ok();
    }

    @PutMapping("/users/{username}/disable")
    public BaseResponse<UserGetResponse> disableUser(@PathVariable String username) {
        return BaseResponse.<UserGetResponse>ok()
                .setPayload(userService.disableUser(username));
    }

    @PutMapping("/users/{username}/enable")
    public BaseResponse<UserGetResponse> enableUser(@PathVariable String username) {
        return BaseResponse.<UserGetResponse>ok()
                .setPayload(userService.enableUser(username));
    }
}
