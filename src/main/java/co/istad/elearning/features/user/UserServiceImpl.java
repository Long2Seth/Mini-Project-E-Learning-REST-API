package co.istad.elearning.features.user;


import co.istad.elearning.features.user.dto.UserGetResponse;
import co.istad.elearning.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;

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
        }
        else {
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
        }
        else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Failed to enable user");
        }
    }
}
