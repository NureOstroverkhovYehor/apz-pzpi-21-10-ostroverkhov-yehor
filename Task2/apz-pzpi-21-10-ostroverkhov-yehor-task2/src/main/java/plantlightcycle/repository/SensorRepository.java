package plantlightcycle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import plantlightcycle.model.PlantEntity;
import plantlightcycle.model.SensorEntity;

import java.util.List;

/**
 * Repository interface for managing SensorEntity objects in the database.
 */
@Repository
public interface SensorRepository extends JpaRepository<SensorEntity, Long> {
    /**
     * Finds all sensors associated with a specific plant by its ID.
     *
     * @param plantId The ID of the plant.
     * @return A list of SensorEntity objects associated with the plant.
     */
    List<SensorEntity> findAllByPlantId(Long plantId);

    /**
     * Finds a sensor by its name and associated plant.
     *
     * @param name The name of the sensor.
     * @param plant The associated PlantEntity.
     * @return The SensorEntity with the specified name and plant.
     */
    SensorEntity findByNameAndPlant(String name, PlantEntity plant);
}
