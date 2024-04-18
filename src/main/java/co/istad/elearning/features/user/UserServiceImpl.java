package co.istad.elearning.features.user;

import co.istad.elearning.domain.User;
import co.istad.elearning.features.user.dto.UserDetailResponse;
import co.istad.elearning.features.user.dto.UserResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;
@Service
@RequiredArgsConstructor
@Data
public class UserServiceImpl implements UserService{


    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public UserDetailResponse disableUser(String username) {
        int affectedRow = userRepository.disable(username);
        if (affectedRow > 0) {
            return userMapper.mapToUserResponse(userRepository.findUserByUsername(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User with username %s not found!", username))));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User with username %s not found!", username));
        }
    }

    @Override
    public UserDetailResponse enableUser(String username) {
        int affectedRow = userRepository.enable(username);
        if (affectedRow > 0) {
            return userMapper.mapToUserResponse(userRepository.findUserByUsername(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User with username %s not found!", username))));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User with username %s not found!", username));
        }
    }

    @Override
    public void deleteUser(String username) {
        User user = userRepository.findUserByUsername(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User with username %s not found!", username)));
        userRepository.delete(user);
    }

}




