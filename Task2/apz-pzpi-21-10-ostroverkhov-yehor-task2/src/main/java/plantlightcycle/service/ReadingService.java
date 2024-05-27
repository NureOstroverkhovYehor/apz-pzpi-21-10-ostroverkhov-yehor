package plantlightcycle.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import plantlightcycle.dto.ReadingDto;
import plantlightcycle.model.SensorEntity;
import plantlightcycle.model.ReadingEntity;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service class responsible for reading-related operations.
 */
@Service
@RequiredArgsConstructor
public class ReadingService {
    /**
     * Service for sensor-related operations.
     */
    private final SensorService sensorService;

    /**
     * Retrieves all readings associated with a specific sensor by its ID.
     *
     * @param sensorId The ID of the sensor.
     * @return A set of ReadingEntity objects associated with the sensor.
     */
    public Set<ReadingEntity> getAllSensorReadings(Long sensorId) {
        SensorEntity sensor = sensorService.getSensorById(sensorId);

        return sensor.getReadings();
    }

    /**
     * Saves a reading based on the provided ReadingDto.
     *
     * @param readingDto The ReadingDto containing reading information.
     * @return The set of ReadingEntity objects associated with the sensor after saving.
     */
    public Set<ReadingEntity> saveReading(ReadingDto readingDto) {
        ReadingEntity reading = new ReadingEntity(readingDto.name(), readingDto.value(), readingDto.isWarning());

        SensorEntity sensor = sensorService.getSensorById(readingDto.sensorId());

        reading.setSensor(sensor);
        sensor.addReading(reading);

        return sensorService.saveSensor(sensor).getReadings();
    }

    /**
     * Retrieves all warning readings associated with sensors owned by a specific plant.
     *
     * @param plantId The ID of the plant.
     * @return A set of ReadingEntity objects considered as warnings for the plant's sensors.
     */
    public Set<ReadingEntity> getAllPlantWarnings(Long plantId) {
        return sensorService.getAllSensorsByPlantId(plantId).stream()
                .flatMap(sensor -> sensor.getReadings().stream())
                .filter(ReadingEntity::isWarning)
                .collect(Collectors.toSet());
    }
}
