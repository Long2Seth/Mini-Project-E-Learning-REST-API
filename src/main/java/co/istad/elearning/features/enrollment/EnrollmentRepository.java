package co.istad.elearning.features.enrollment;

import co.istad.elearning.domain.Enrollment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long>{

    Optional<Enrollment> findByCode(Integer code);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.isDeleted = :status WHERE u.id = :code")
    int disableByCode  (String code, boolean status);
}
