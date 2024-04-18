package co.istad.elearning.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "enrollments")
@Data
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate certifiedAt;

    @Column(nullable = false)
    private Integer code;

    private LocalDate enrolledAt;

    private Boolean isCertified;

    private Boolean isDeleted;

    private Integer progress;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private Student student;


}
