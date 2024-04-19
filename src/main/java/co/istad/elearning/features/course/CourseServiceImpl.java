package co.istad.elearning.features.course;

import co.istad.elearning.base.BasedMessage;
import co.istad.elearning.domain.Category;
import co.istad.elearning.domain.Course;
import co.istad.elearning.features.category.CategoryRepository;
import co.istad.elearning.features.course.dto.*;
import co.istad.elearning.features.instuctor.InstructorRepository;
import co.istad.elearning.mapper.CourseMapper;
import jakarta.transaction.Transactional;
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
    private final CategoryRepository categoryRepository;
    private final InstructorRepository instructorRepository;

    @Override
    public Page<CourseResponse> getCourses(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Course> courses = courseRepository.findAll(pageRequest);
        return courses.map(courseMapper::toCourseResponse);
    }

    @Override
    public BasedMessage createCourse(CourseCreateRequest courseCreateRequest) {
        if (courseRepository.existsByAlias(courseCreateRequest.alias())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Alias are already existing in our system..!"
            );
        }
        if (!categoryRepository.existsById(courseCreateRequest.categoryId().getId())) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "The Category ID (" + courseCreateRequest.categoryId().getId() + ") don't have in our system...!"
            );
        }
        Course course = null;
        if (!instructorRepository.existsById(courseCreateRequest.instructorId().getId())) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "The Instructor ID (" + courseCreateRequest.instructorId().getId() + ") don't have in our system...!"
            );
        }
        course = courseMapper.fromCourseCreateRequest(courseCreateRequest);
        course.setIsDeleted(false);
        course.setIsFree(false);
        courseRepository.save(course);
        return new BasedMessage("Course has been added....!");
    }


    @Override
    public CourseDetailsResponse findByAlias(String alias) {
        Course course = courseRepository.findAllByAlias(alias).
                orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Course not found...!"
                        )
                );
        return courseMapper.toCourseDetailResponse(course);
    }

    @Transactional
    @Override
    public BasedMessage updateThumbnail(CourseThumbnailRequest coursethumbnailRequest, String alias) {
        if (!courseRepository.existsByAlias(alias)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Course Not Found..."
            );
        }
        courseRepository.updateThumbnail(alias, coursethumbnailRequest.thumbnail());
        return new BasedMessage("Course Thumbnail  has been updated....!");
    }

    @Override
    public BasedMessage updateCourseByAlias(String alias, CourseUpdateRequest courseUpdateRequest) {
        Course course = courseRepository.findByAlias(alias).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Course has not been found...!"
                )
        );
        course.setAlias(courseUpdateRequest.alias());
        course.setDescription(courseUpdateRequest.description());
        course.setTitle(courseUpdateRequest.title());
        courseRepository.save(course);
        return new BasedMessage("Course's has been updated...!");
    }

    @Override
    public BasedMessage updateCourseCategory(String alias, CourseCategoryRequest courseCategoryRequest) {
        if (!courseRepository.existsByAlias(alias)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Course has not been found...!"
            );
        }

        Long categoryId = Long.parseLong(courseCategoryRequest.category().getId() + "");
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Category not found...!"
                ));
        courseRepository.updateCourseCategory(alias, category);

        return new BasedMessage("Course's Category has been updated...!");

    }

    @Override
    public BasedMessage disableCourse(String alias) {
        Course course = courseRepository.findByAlias(alias)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Disabled Course Not Found...!")
                );
        courseRepository.disableCourse(alias);
        return new BasedMessage("Course has been disabled....!");
    }

    @Override
    public BasedMessage enableCourse(String alias) {
        Course course = courseRepository.findByAlias(alias).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Enable Course Not Found...!")
        );
        courseRepository.enableCourse(alias);
        return new BasedMessage("Course has been enabled....!");
    }
}

