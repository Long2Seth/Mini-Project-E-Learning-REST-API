package co.istad.elearning.features.country;

import co.istad.elearning.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country,Long> {
    List<Country> findByNameContainingIgnoreCase(String name);
    Country findByIso(String iso);
}