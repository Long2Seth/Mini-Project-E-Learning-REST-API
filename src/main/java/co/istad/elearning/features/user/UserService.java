package co.istad.elearning.features.user;

import co.istad.elearning.features.user.dto.UserGetResponse;
import co.istad.elearning.features.user.dto.RoleAuthorityResponse;

import java.util.List;

public interface UserService {
    List<UserGetResponse> getAllUsers();
    UserGetResponse findUserByUsername(String username);
//    UserResponse createUser(UserCreateRequest userCreateRequest);
//    UserResponse updateUser(String username, UserCreateRequest userCreateRequest);
    void deleteUser(String username);
    UserGetResponse disableUser(String username);
    UserGetResponse enableUser(String username);

    List<RoleAuthorityResponse> findAllRoles();

    RoleAuthorityResponse findRoleByRoleName(String role);
}
