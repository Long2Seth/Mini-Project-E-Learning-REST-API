package co.istad.elearning.features.user;

import co.istad.elearning.domain.Authority;
import co.istad.elearning.domain.Role;
import co.istad.elearning.features.user.dto.RoleAuthorityResponse;
import co.istad.elearning.mapper.RoleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final RoleRepository roleRepository;
    private final AuthorityRepository authorityRepository;

    private final RoleMapper roleMapper;

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

        if(roleRepository.existsByName(rolename)){
            Role foundRole = roleRepository.findRoleByName(rolename);
            return roleMapper.toRoleAuthorityResponse(foundRole);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                               "Role " + rolename +" has not been found!"
                        );
        }

    }
}
