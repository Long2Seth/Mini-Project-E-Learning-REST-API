package co.istad.elearning.features.user;

import co.istad.elearning.domain.Role;
import co.istad.elearning.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByNationalIdCard (String nationalIdCard);

    boolean existsByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.username = :username AND :role MEMBER OF u.roles")
    Optional<User> findByUsernameAndRoles(@Param("username") String username, @Param("role") Role role);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.isVerified = :status WHERE u.username = :userName")
    int updateBlockedStatusById(String userName, boolean status);


}
