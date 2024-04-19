package co.istad.elearning.mapper;

import co.istad.elearning.domain.Instructor;
import co.istad.elearning.domain.User;
import co.istad.elearning.features.instuctor.dto.InstructorResponse;
import co.istad.elearning.features.user.dto.UserCreateRequest;
import co.istad.elearning.features.user.dto.UserDetailResponse;
import co.istad.elearning.features.user.dto.UserGetResponse;
import co.istad.elearning.features.user.dto.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User fromUserCreateRequest(UserCreateRequest userCreateRequest);


    @Mapping(target = "country", expression = "java(user.getCountry() != null ? user.getCountry().getName() : null)")
    @Mapping(target = "city", expression = "java(user.getCity() != null ? user.getCity().getName() : null)")
    UserGetResponse toUserDetailResponse(User user);

    UserResponse toUserResponse(User user);

}
