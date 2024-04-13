package co.istad.elearning.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "cities")
@Data
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 60,nullable = false)
    private String name;

    //relationship

    @OneToMany(mappedBy = "city")
    private List<User> users;

    //country_id

}
