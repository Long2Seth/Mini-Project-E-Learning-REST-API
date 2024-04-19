package co.istad.elearning.domain;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "countries")
@Data
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 500)
    private String flag;

    @Column(length = 10,nullable = false)
    private String iso;

    @Column(length = 60,nullable = false)
    private String name;

    private String niceName;

    @Column(nullable = false)
    private Integer numCode;

    @Column(nullable = false)
    private Integer phoneCode;

    //relationship
    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private List<City> cities;

}
