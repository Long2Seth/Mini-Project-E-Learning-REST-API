package co.istad.elearning.features.user;

import co.istad.elearning.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByNationalIdCard (String nationalIdCard);

    boolean existsByEmail(String email);
}
