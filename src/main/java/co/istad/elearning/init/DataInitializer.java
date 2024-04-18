package co.istad.elearning.init;

import co.istad.elearning.domain.Category;
import co.istad.elearning.domain.Role;
import co.istad.elearning.domain.Student;
import co.istad.elearning.features.category.CategoryRepository;
import co.istad.elearning.features.enrollment.EnrollmentService;
import co.istad.elearning.features.enrollment.dto.EnrollmentRequest;
import co.istad.elearning.features.student.StudentRepository;
import co.istad.elearning.features.user.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final RoleRepository roleRepository;
    private final CategoryRepository categoryRepository;
    private final EnrollmentService enrollmentService;
    private final StudentRepository studentRepository;

    @PostConstruct
    void initRole() {

        if (roleRepository.count() < 1) {
            Role user = new Role();
            user.setName("USER");

            Role student = new Role();
            student.setName("STUDENT");

            Role instructor = new Role();
            instructor.setName("INSTRUCTOR");

            Role admin = new Role();
            admin.setName("ADMIN");

            roleRepository.saveAll(
                    List.of(user, student, instructor, admin)
            );
        }

    }


    @PostConstruct
    void initCategory() {
//        Category parentCategory = new Category()
//                .setName("Parent Category")
//                .setAlias("parent_category")
//                .setIcon("icon-url")
//                .setIsDeleted(false);
//
//        parentCategory = categoryRepository.save(parentCategory);

        List<Category> categories = new ArrayList<>();
        categories.add(new Category()
                .setName("Java")
                .setAlias("java")
                .setIcon("java-icon.png")
                .setIsDeleted(false));

        categories.add(new Category()
                .setName("Big Data")
                .setAlias("big-data")
                .setIcon("big-data-icon.png")
                .setIsDeleted(false));

        categories.add(new Category()
                .setName("Python")
                .setAlias("python")
                .setIcon("python-icon.png")
                .setIsDeleted(false));

        categoryRepository.saveAll(categories);
    }

    @PostConstruct
    void initEnrollment(){
        createEnrollmentIfStudentExists("student1", 1L, 1L);
        createEnrollmentIfStudentExists("student2", 2L, 2L);
        createEnrollmentIfStudentExists("student3", 3L, 3L);
    }

    private void createEnrollmentIfStudentExists(String code, Long courseId, Long studentId) {
        Optional<Student> studentOptional = studentRepository.findById(Math.toIntExact(studentId));
        if (studentOptional.isPresent()) {
            EnrollmentRequest enrollmentRequest = new EnrollmentRequest(code, courseId, studentId);
            enrollmentService.createEnrollment(enrollmentRequest);
        } else {
            // handle the case where the student does not exist
            System.out.println("Student with ID: " + studentId + " does not exist!");
        }
    }
}
