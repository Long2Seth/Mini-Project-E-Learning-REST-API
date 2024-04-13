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
    private Integer id;

    private LocalDate certifiedAt;

    @Column(nullable = false)
    private Integer code;

    private LocalDate enrolledAt;

    private Boolean isCertified;

    private Boolean isDeleted;

    private Integer progress;

    //relationship
        //student_id


}
