package co.istad.elearning.features.instuctor;

import co.istad.elearning.domain.Instructor;
import co.istad.elearning.features.instuctor.dto.InstructorCreateRequest;
import co.istad.elearning.features.user.dto.UserCreateRequest;

public interface InstructorService {
    void createNew(UserCreateRequest userCreateRequest, InstructorCreateRequest instructorCreateRequest);
}
