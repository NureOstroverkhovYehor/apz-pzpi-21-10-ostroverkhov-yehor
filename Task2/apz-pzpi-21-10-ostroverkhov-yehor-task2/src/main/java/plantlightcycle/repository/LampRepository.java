package plantlightcycle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import plantlightcycle.model.LampEntity;

import java.util.List;

/**
 * Repository interface for LampEntity to perform CRUD operations.
 */
@Repository
public interface LampRepository extends JpaRepository<LampEntity, Long> {
    /**
     * Retrieves a lamp by its name.
     *
     * @param name The name of the lamp to retrieve.
     * @return The LampEntity with the specified name.
     */
    LampEntity findByName(String name);

    /**
     * Retrieves all lamps associated with a specific sensor ID.
     *
     * @param sensorId The ID of the sensor to retrieve lamps for.
     * @return A list of LampEntity objects associated with the specified sensor ID.
     */
    List<LampEntity> findAllBySensorId(Long sensorId);
}
