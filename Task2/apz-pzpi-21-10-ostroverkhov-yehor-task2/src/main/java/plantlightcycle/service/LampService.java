package plantlightcycle.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import plantlightcycle.dto.DeletionLampDto;
import plantlightcycle.dto.LampDto;
import plantlightcycle.dto.LampEditingDto;
import plantlightcycle.model.SensorEntity;
import plantlightcycle.model.LampEntity;
import plantlightcycle.repository.LampRepository;

import java.util.List;
import java.util.Set;

/**
 * Represents a lamp entity within the system.
 * This entity contains information about a lamp's ID, association with a sensor,
 * name, and light level.
 */
@Service
@RequiredArgsConstructor
public class LampService {
    /**
     * Repository for LampEntity to perform CRUD operations.
     */
    private final LampRepository lampRepository;

    /**
     * Service for SensorEntity to perform sensor-related operations.
     */
    private final SensorService sensorService;

    /**
     * Retrieves all lamps present in the system.
     *
     * @return A list of all LampEntity objects.
     */
    public List<LampEntity> getAllLamps() {
        return lampRepository.findAll();
    }

    /**
     * Retrieves all lamps associated with a specific sensor.
     *
     * @param sensorId The ID of the sensor.
     * @return A list of LampEntity objects associated with the specified sensor ID.
     */
    public List<LampEntity> getAllSensorLamps(Long sensorId) {
        return lampRepository.findAllBySensorId(sensorId);
    }

    /**
     * Saves a new lamp associated with a specific sensor.
     *
     * @param lampDto The LampDto object containing lamp details.
     * @return A set of LampEntity objects associated with the sensor after saving the new lamp.
     */
    public Set<LampEntity> saveLamp(LampDto lampDto) {
        SensorEntity sensor = sensorService.getSensorById(lampDto.sensorId());

        LampEntity lamp = new LampEntity(lampDto.name(), lampDto.lightLevel(), lampDto.spectrum());

        lamp.setSensor(sensor);
        sensor.addLamp(lamp);

        return sensorService.saveSensor(sensor).getLamps();
    }

    /**
     * Edits the light level of a lamp associated with a specific sensor.
     *
     * @param lampEditingDto The LampEditingDto object containing lamp editing details.
     * @return A set of LampEntity objects associated with the sensor after editing the lamp.
     */
    public Set<LampEntity> editLamp(LampEditingDto lampEditingDto) {
        SensorEntity sensor = sensorService.getSensorById(lampEditingDto.sensorId());

        LampEntity lamp = sensor.getLamps().stream().filter(sensorLamp -> sensorLamp.getName().equals(lampEditingDto.name())).toList().get(0);

        sensor.removeLamp(lamp);

        lamp.setLightLevel(lampEditingDto.newValue());

        sensor.addLamp(lamp);

        return sensorService.saveSensor(sensor).getLamps();
    }

    /**
     * Removes a lamp from a specific sensor.
     *
     * @param deletionLampDto The DeletionLampDto object containing details to delete the lamp.
     */
    public Set<LampEntity> removeLampFromSensor(DeletionLampDto deletionLampDto) {
        SensorEntity sensor = sensorService.getSensorById(deletionLampDto.sensorId());

        LampEntity lamp = lampRepository.findByName(deletionLampDto.name());

        sensor.removeLamp(lamp);
        return sensorService.saveSensor(sensor).getLamps();
    }
}
