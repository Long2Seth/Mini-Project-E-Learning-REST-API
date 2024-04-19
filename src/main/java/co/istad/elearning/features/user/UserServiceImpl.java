package co.istad.elearning.features.user;


import co.istad.elearning.features.user.dto.UserGetResponse;
import co.istad.elearning.mapper.UserMapper;
import lombok.AllArgsConstructor;
import co.istad.elearning.domain.Authority;
import co.istad.elearning.domain.Role;
import co.istad.elearning.features.user.dto.RoleAuthorityResponse;
import co.istad.elearning.mapper.RoleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final AuthorityRepository authorityRepository;

    private final RoleMapper roleMapper;

    @Override
    public List<UserGetResponse> getAllUsers() {
        return userRepository.findAll()
                .stream().map(userMapper::toUserDetailResponse).toList();
    }

    @Override
    public UserGetResponse findUserByUsername(String username) {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toUserDetailResponse(user);
    }

//    @Override
//    public UserResponse createUser(UserCreateRequest userCreateRequest) {
//        return null;
//    }

//    @Override
//    public UserResponse updateUser(String username, UserCreateRequest userCreateRequest) {
//        return null;
//    }

    @Override
    public void deleteUser(String username) {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }

    @Override
    public UserGetResponse disableUser(String username) {
        var useEffectRow = userRepository.updateBlockedStatusById(username, true);
        if (useEffectRow > 0) {
            return userMapper.toUserDetailResponse(
                    userRepository.findByUsername(username)
                            .orElseThrow(null));
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Failed to disable user");
        }
    }

    @Override
    public UserGetResponse enableUser(String username) {
        var useEffectRow = userRepository.updateBlockedStatusById(username, false);
        if (useEffectRow > 0) {
            return userMapper.toUserDetailResponse(
                    userRepository.findByUsername(username)
                            .orElseThrow(null));
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Failed to enable user");
        }

    }

    @Override
    public List<RoleAuthorityResponse> findAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream()
                .map(roleMapper::toRoleAuthorityResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RoleAuthorityResponse findRoleByRoleName(String rolename) {
//        Role foundRole = roleRepository.findByName(rolename)
//                .orElseThrow(() ->
//                        new ResponseStatusException(HttpStatus.NOT_FOUND,
//                                "Role " + rolename +" has not been found!"
//                        ));

        if (roleRepository.existsByName(rolename)) {
            Role foundRole = roleRepository.findRoleByName(rolename);
            return roleMapper.toRoleAuthorityResponse(foundRole);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Role " + rolename + " has not been found!"
            );
        }

    }
}
