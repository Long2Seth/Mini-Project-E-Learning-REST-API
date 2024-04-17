package co.istad.elearning.features.student;

import co.istad.elearning.domain.Instructor;
import co.istad.elearning.domain.Role;
import co.istad.elearning.domain.Student;
import co.istad.elearning.domain.User;
import co.istad.elearning.features.student.dto.StudentCreateRequest;
import co.istad.elearning.features.student.dto.StudentResponse;
import co.istad.elearning.features.user.RoleRepository;
import co.istad.elearning.features.user.UserRepository;
import co.istad.elearning.features.user.dto.UserCreateRequest;
import co.istad.elearning.mapper.StudentMapper;
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
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final UserMapper userMapper;
    private final StudentMapper studentMapper;

    @Override
    public StudentResponse createNew(UserCreateRequest userCreateRequest, StudentCreateRequest studentCreateRequest) {
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
        //this is default profile picture
        user.setProfile("097213a0-9838-4bd5-851c-fa55e2fa3ddd.png");

        // Assign default user role as USER and student
        List<Role> roles = new ArrayList<>();

        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Role USER has not been found!"));

        roles.add(userRole);
        user.setRoles(roles);

        Role studentRole = roleRepository.findByName("STUDENT")
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Role STUDENT has not been found!"
                        ));

        roles.add(studentRole);
        user.setRoles(roles);

        userRepository.save(user);

        //Create new student
        Student student = studentMapper.fromStudentCreateRequest(studentCreateRequest);
        student.setIsBlocked(false);
        student.setUser(user);

        studentRepository.save(student);

        return studentMapper.toStudentResponse(student);
    }
}
