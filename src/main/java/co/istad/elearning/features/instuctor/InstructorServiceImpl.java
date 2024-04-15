package co.istad.elearning.features.instuctor;

import co.istad.elearning.domain.Instructor;
import co.istad.elearning.domain.Role;
import co.istad.elearning.domain.User;
import co.istad.elearning.features.instuctor.dto.InstructorCreateRequest;
import co.istad.elearning.features.user.RoleRepository;
import co.istad.elearning.features.user.UserRepository;
import co.istad.elearning.features.user.dto.UserCreateRequest;
import co.istad.elearning.mapper.InstructorMapper;
import co.istad.elearning.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService{

    private final InstructorRepository instructorRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final InstructorMapper instructorMapper;
    private final UserMapper userMapper;

    @Override
    public void createNew(UserCreateRequest userCreateRequest, InstructorCreateRequest instructorCreateRequest ) {

        //create new user
        if (userRepository.existsByPhoneNumber(userCreateRequest.phoneNumber())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Phone number has already been existed!"
            );
        }

        if (userRepository.existsByNationalIdCard(userCreateRequest.nationalIdCard())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "National card ID has already been existed!"
            );
        }

        if(userRepository.existsByEmail(userCreateRequest.email())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Email has already been existed!"
            );
        }

        if (!userCreateRequest.password()
                .equals(userCreateRequest.confirmedPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Password doesn't match!"
            );
        }

        User user = userMapper.fromUserCreateRequest(userCreateRequest);
        user.setUuid(UUID.randomUUID().toString());
        user.setIsDeleted(false);
        user.setIsVerified(true);
        user.setProfile("avatar.png");

        // Assign default user role as USER and INSTRUCTOR
        List<Role> roles = new ArrayList<>();

        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Role USER has not been found!"));

        roles.add(userRole);
        user.setRoles(roles);

        Role instructorRole = roleRepository.findByName("INSTRUCTOR")
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Role INSTRUCTOR has not been found!"
                        ));

        roles.add(instructorRole);
        user.setRoles(roles);

        userRepository.save(user);

        //Create new instructor
        Instructor instructor = instructorMapper.fromInstructorCreateRequest(instructorCreateRequest);
        instructor.setIsBlocked(false);
        instructor.setUser(user);

        instructorRepository.save(instructor);

    }
}
