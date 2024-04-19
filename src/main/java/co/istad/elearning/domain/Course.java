package co.istad.elearning.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Table(name = "courses")
@Data
@Accessors(chain = true)
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

    @ManyToOne
    private Category category;

    @ManyToOne
    private Instructor instructor;
}
