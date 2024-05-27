package plantlightcycle.controller;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import plantlightcycle.dto.DeletionSensorDto;
import plantlightcycle.dto.SensorDto;
import plantlightcycle.model.SensorEntity;
import plantlightcycle.service.SensorService;

import java.util.List;
import java.util.Set;

/**
 * Controller handling sensor-related endpoints.
 */
@RestController
@RequestMapping("/sensors")
@RequiredArgsConstructor
public class SensorController {
    /**
     * Service responsible for sensor-related operations.
     */
    private final SensorService sensorService;

    @GetMapping
    public ResponseEntity<List<SensorEntity>> getAllSensors() {
        return ResponseEntity.ok(sensorService.getAllSensors());
    }

    /**
     * Retrieves all sensors associated with a particular plant.
     *
     * @param plantId Plant ID
     * @return ResponseEntity containing a list of SensorEntity associated with the plant
     */
    @GetMapping("/{plantId}")
    public ResponseEntity<List<SensorEntity>> getAllPlantSensors(@PathVariable Long plantId) {
        return ResponseEntity.ok(sensorService.getAllSensorsByPlantId(plantId));
    }

    /**
     * Saves a new sensor.
     *
     * @param sensorDto Sensor information to be saved
     * @return ResponseEntity containing a set of all SensorEntity corresponding to the plant
     * and HTTP status CREATED
     */
    @PostMapping
    public ResponseEntity<Set<SensorEntity>> saveSensor(@RequestBody SensorDto sensorDto) {
        return new ResponseEntity<>(sensorService.saveSensor(sensorDto), HttpStatus.CREATED);
    }

    /**
     * Deletes a sensor from a plant's list of sensors.
     *
     * @param deletionSensorDto Information about the sensor to be deleted from the plant
     * @return ResponseEntity with all sensors retrieved by plant ID
     */
    @DeleteMapping
    public ResponseEntity<List<SensorEntity>> deleteSensorFromPlant(@RequestBody DeletionSensorDto deletionSensorDto) {
        sensorService.deleteSensorFromPlant(deletionSensorDto);
        return ResponseEntity.ok(sensorService.getAllSensorsByPlantId(deletionSensorDto.plantId()));
    }
}
