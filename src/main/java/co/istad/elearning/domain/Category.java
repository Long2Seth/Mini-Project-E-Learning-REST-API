package co.istad.elearning.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "categories")
@Accessors(chain = true)
@Data

public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String alias;

    private String icon;

    private Boolean isDeleted;

    @Column(length = 60, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category_id")
    @JsonIgnore
    private Category parentCategory;
//    private Integer parentCategoryId;

    @OneToMany(mappedBy = "category")
    private List<Course> courses;
}
