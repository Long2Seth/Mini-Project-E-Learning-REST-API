package co.istad.elearning.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@Accessors(chain = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String uuid;

    private String address1;

    private String address2;

    private LocalDate dob;

    @Column(unique = true,nullable = false)
    private String email;

    @Column(length = 50)
    private String familyName;

    @Column(length = 8)
    private String gender;

    @Column(length = 50)
    private String givenName;

    private Boolean isDeleted;

    private Boolean isVerified;

    @Column(length = 30,unique = true,nullable = false)
    private String nationalIdCard;

    private String password;

    @Column(length = 30,nullable = false)
    private String phoneNumber;

    private String profile;

    @Column(length = 30,nullable = false)
    private String username;

    //relationship
    @ManyToOne
    private City city;

    @ManyToOne
    private Country country;

    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

    @OneToMany(mappedBy = "user")
    private List<Student> students;

    @OneToMany(mappedBy = "user")
    private List<Instructor> instructors;












}
