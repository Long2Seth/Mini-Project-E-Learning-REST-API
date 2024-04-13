package co.istad.elearning.domain;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "instructors")
@Data
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String biography;

    private String github;

    private Boolean isBlocked;

    private String jobTitle;

    private String linkIn;

    private String website;

    //relationship
        //user_id
}
