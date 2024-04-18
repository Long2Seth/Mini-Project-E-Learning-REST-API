package co.istad.elearning.mapper;

import co.istad.elearning.domain.Student;
import co.istad.elearning.features.instuctor.dto.InstructorResponse;
import co.istad.elearning.features.student.dto.StudentCreateRequest;
import co.istad.elearning.features.student.dto.StudentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" )
public interface StudentMapper {
    Student fromStudentCreateRequest(StudentCreateRequest studentCreateRequest);

    @Mapping(source = "user", target = "userDetailResponse")
    StudentResponse toStudentResponse(Student student);

}
