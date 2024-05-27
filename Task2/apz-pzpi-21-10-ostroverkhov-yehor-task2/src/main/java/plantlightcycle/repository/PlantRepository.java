package plantlightcycle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import plantlightcycle.model.PlantEntity;

@Repository
public interface PlantRepository extends JpaRepository<PlantEntity, Long> {
}
