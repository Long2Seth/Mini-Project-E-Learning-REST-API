package co.istad.elearning.features.user;

import co.istad.elearning.features.user.dto.RoleAuthorityResponse;

import java.util.List;

public interface UserService {
    List<RoleAuthorityResponse> findAllRoles();

    RoleAuthorityResponse findRoleByRoleName(String role);
}
