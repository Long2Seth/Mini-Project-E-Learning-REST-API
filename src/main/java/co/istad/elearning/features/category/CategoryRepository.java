package co.istad.elearning.features.category;

import co.istad.elearning.domain.Category;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Category AS c SET c.isDeleted = :status WHERE c.id = :id")
    int updateBlockedStatusById(Long id, boolean status);

}
