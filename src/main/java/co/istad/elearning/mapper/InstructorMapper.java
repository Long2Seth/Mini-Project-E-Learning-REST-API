package co.istad.elearning.mapper;

import co.istad.elearning.domain.Instructor;
import co.istad.elearning.features.instuctor.dto.InstructorCreateRequest;
import co.istad.elearning.features.instuctor.dto.InstructorResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InstructorMapper {

    Instructor fromInstructorCreateRequest(InstructorCreateRequest instructorCreateRequest);

    InstructorResponse toInstructorResponse(Instructor instructor);

    List<InstructorResponse> toInstructorResponseList(List<Instructor> instructors);
}
