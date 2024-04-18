package co.istad.elearning.features.user;

import co.istad.elearning.domain.Role;
import co.istad.elearning.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByNationalIdCard (String nationalIdCard);

    boolean existsByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.username = :username AND :role MEMBER OF u.roles")
    Optional<User> findByUsernameAndRoles(@Param("username") String username, @Param("role") Role role);


}
