package co.istad.elearning.mapper;

import co.istad.elearning.domain.Role;
import co.istad.elearning.features.user.dto.RoleAuthorityResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" )
public interface RoleMapper {

    @Mapping(target = "name")
    RoleAuthorityResponse toRoleAuthorityResponse(Role role);

}
