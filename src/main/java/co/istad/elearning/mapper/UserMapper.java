package co.istad.elearning.mapper;

import co.istad.elearning.domain.Instructor;
import co.istad.elearning.domain.User;
import co.istad.elearning.features.instuctor.dto.InstructorResponse;
import co.istad.elearning.features.user.dto.UserCreateRequest;
import co.istad.elearning.features.user.dto.UserDetailResponse;
import co.istad.elearning.features.user.dto.UserResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User fromUserCreateRequest(UserCreateRequest userCreateRequest);

    UserDetailResponse toUserDetailResponse(User user);

    UserResponse toUserResponse(User user);


}
