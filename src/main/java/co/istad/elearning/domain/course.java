package co.istad.elearning.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "courses")
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String alias;

    private String description;

    private Boolean isDeleted;

    private Boolean isFree;

    private String thumbnail;

    private String title;

    //relationship

        //cat_id

        //ins_id
}
