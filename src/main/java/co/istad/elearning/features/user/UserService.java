package co.istad.elearning.features.user;

import co.istad.elearning.domain.User;
import co.istad.elearning.features.user.dto.UserDetailResponse;
import co.istad.elearning.features.user.dto.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface UserService {


    UserDetailResponse disableUser(String username);

    UserDetailResponse enableUser(String username);

    void deleteUser(String username);

}
