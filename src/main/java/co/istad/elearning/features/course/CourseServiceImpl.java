package co.istad.elearning.features.course;

import co.istad.elearning.domain.Course;
import co.istad.elearning.features.course.CourseRepository;
import co.istad.elearning.features.course.CourseService;
import co.istad.elearning.features.course.dto.CourseCreateRequest;
import co.istad.elearning.features.course.dto.CourseDetailsResponse;
import co.istad.elearning.features.course.dto.CourseResponse;
import co.istad.elearning.mapper.CourseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    @Override
    public Page<CourseResponse> findList(int page, int size) {
        if (page < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Page number must be greater than or equal to zero");
        }

        if (size < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Size must be greater than or equal to one");
        }

        Sort sortByActName = Sort.by(Sort.Direction.ASC, "actName");
        PageRequest pageRequest = PageRequest.of(page, size, sortByActName);

        Page<Course> accounts = courseRepository.findAll(pageRequest);

        return accounts.map(courseMapper::toCourseResponse);
    }

    @Override
    public void createNew(CourseCreateRequest courseCreateRequest) {

    }

    @Override
    public CourseDetailsResponse findByAlias(String alias) {
        return null;
    }
}
