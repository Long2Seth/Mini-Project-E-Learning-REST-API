package co.istad.elearning.features.user;

import co.istad.elearning.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(String name);

    Role findRoleByName(String name);

    boolean existsByName(String name);
}
