package co.istad.elearning.features.user;

import co.istad.elearning.features.user.dto.RoleAuthorityResponse;
import co.istad.elearning.features.user.dto.RoleRequest;
import co.istad.elearning.util.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    @GetMapping("/roles")
    public BaseResponse<?> findAllRoles(){
        return BaseResponse.ok()
                .setPayload(userService.findAllRoles());
    }


    @GetMapping("/roles/{name}")
    public BaseResponse<?> findRoleByName(@PathVariable String name){
        return BaseResponse.ok()
                .setPayload(userService.findRoleByRoleName(name));
    }
}
