package co.istad.elearning.init;

import co.istad.elearning.domain.Category;
import co.istad.elearning.domain.Role;
import co.istad.elearning.features.category.CategoryRepository;
import co.istad.elearning.features.user.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final RoleRepository roleRepository;
    private final CategoryRepository categoryRepository;

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
}
