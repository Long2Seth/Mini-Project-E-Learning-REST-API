package co.istad.elearning.init;

import co.istad.elearning.domain.Category;
import co.istad.elearning.features.category.CategoryRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class DataInitializer {

    private final CategoryRepository categoryRepository;


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
