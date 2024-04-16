package co.istad.elearning.features.user;

import co.istad.elearning.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByNationalIdCard (String nationalIdCard);

    boolean existsByEmail(String email);

    Optional<User> findByUsername(String username);


}
