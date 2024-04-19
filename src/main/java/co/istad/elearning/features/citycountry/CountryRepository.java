package co.istad.elearning.features.citycountry;

import co.istad.elearning.domain.Country;
import co.istad.elearning.features.citycountry.dto.CityResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country,Integer> {
    Optional<Country> findByName(String name);
}
