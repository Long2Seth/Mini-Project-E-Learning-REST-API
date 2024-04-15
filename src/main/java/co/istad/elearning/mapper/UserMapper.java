package co.istad.elearning.mapper;

import co.istad.elearning.domain.User;
import co.istad.elearning.features.user.dto.UserCreateRequest;
import co.istad.elearning.features.user.dto.UserDetailResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User fromUserCreateRequest(UserCreateRequest userCreateRequest);

    UserDetailResponse toUserDetailResponse(User user);

}
