package co.istad.elearning.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "categories")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100,nullable = false)
    private String alias;

    private String icon;

    private Boolean isDeleted;

    @Column(length = 60,nullable = false)
    private String name;

    //relationship
    private Integer parentCategoryId;
}
