package co.istad.elearning.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "students")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String highSchool;

    private Boolean isBlocked;

    private String university;

    //relationship
        //user_id
}
