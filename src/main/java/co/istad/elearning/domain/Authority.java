package co.istad.elearning.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authorities")
@Data
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    //relationship
//    @ManyToMany(mappedBy = "authorities")
//    private List<Role> roles  = new ArrayList<>();
}
