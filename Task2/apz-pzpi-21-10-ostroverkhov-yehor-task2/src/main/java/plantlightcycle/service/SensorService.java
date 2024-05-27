package plantlightcycle.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import plantlightcycle.dto.DeletionSensorDto;
import plantlightcycle.dto.SensorDto;
import plantlightcycle.model.PlantEntity;
import plantlightcycle.model.SensorEntity;
import plantlightcycle.repository.SensorRepository;

import java.util.List;
import java.util.Set;

/**
 * Service class responsible for sensor-related operations.
 */
@Service
@RequiredArgsConstructor
public class SensorService {
    /**
     * Repository for managing SensorEntity objects.
     */
    private final SensorRepository sensorRepository;

    /**
     * Service for plant-related operations.
     */
    private final PlantService plantService;

    /**
     * Retrieves all sensors.
     *
     * @return A list of all SensorEntity objects.
     */
    public List<SensorEntity> getAllSensors() {
        return sensorRepository.findAll();
    }

    /**
     * Retrieves a sensor by its ID.
     *
     * @param sensorId The ID of the sensor to retrieve.
     * @return The SensorEntity associated with the provided ID.
     * @throws EntityNotFoundException if the sensor with the given ID is not found.
     */
    public SensorEntity getSensorById(Long sensorId) {
        return sensorRepository.findById(sensorId).orElseThrow(EntityNotFoundException::new);
    }

    /**
     * Retrieves all sensors associated with a specific plant by its id.
     *
     * @param plantId The ID of the plant.
     * @return A list of SensorEntity objects associated with the plant.
     */
    public List<SensorEntity> getAllSensorsByPlantId(Long plantId) {
        return sensorRepository.findAllByPlantId(plantId);
    }

    /**
     * Saves a sensor based on the provided SensorDto.
     *
     * @param sensorDto The SensorDto containing sensor information.
     * @return The set of SensorEntity objects associated with the plant after saving.
     */
    public Set<SensorEntity> saveSensor(SensorDto sensorDto) {
        SensorEntity sensor = new SensorEntity(sensorDto.name());

        PlantEntity plant = plantService.getById(sensorDto.plantId());

        sensor.setPlant(plant);
        plant.addSensor(sensor);

        return plantService.save(plant).getSensors();
    }

    /**
     * Saves a sensor.
     *
     * @param sensor The SensorEntity to be saved.
     * @return The saved SensorEntity.
     */
    public SensorEntity saveSensor(SensorEntity sensor) {
        return sensorRepository.save(sensor);
    }

    /**
     * Deletes a sensor associated with a specific plant based on the provided DeletionSensorDto.
     *
     * @param sensorDto The DeletionSensorDto containing sensor information for deletion.
     */
    public void deleteSensorFromPlant(DeletionSensorDto sensorDto) {
        PlantEntity plant = plantService.getById(sensorDto.plantId());

        SensorEntity sensor = sensorRepository.findByNameAndPlant(sensorDto.name(), plant);

        sensorRepository.delete(sensor);
        plant.removeSensor(sensor);
    }
}
