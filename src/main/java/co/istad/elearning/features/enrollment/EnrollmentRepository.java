package co.istad.elearning.features.enrollment;

import co.istad.elearning.domain.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long>{

    Optional<Enrollment> findByCode(Integer code);
}
