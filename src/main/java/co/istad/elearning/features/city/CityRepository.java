package co.istad.elearning.features.city;


import co.istad.elearning.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {
    List<City> findAll();
    List<City> findByNameContainingIgnoreCase(String name);
    List<City> findByCountryIso(String iso);
}
