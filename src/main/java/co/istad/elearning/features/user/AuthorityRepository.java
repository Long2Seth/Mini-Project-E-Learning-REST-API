package co.istad.elearning.features.user;

import co.istad.elearning.domain.Authority;
import co.istad.elearning.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority,Integer> {
    Optional<Authority> findByName(String name);
}
