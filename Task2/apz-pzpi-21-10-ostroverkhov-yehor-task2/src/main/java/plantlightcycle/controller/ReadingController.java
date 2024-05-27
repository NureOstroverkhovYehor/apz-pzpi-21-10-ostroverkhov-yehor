package plantlightcycle.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import plantlightcycle.dto.ReadingDto;
import plantlightcycle.model.ReadingEntity;
import plantlightcycle.service.ReadingService;

import java.util.Set;

/**
 * Controller managing endpoints related to readings.
 */
@RestController
@RequestMapping("/readings")
@RequiredArgsConstructor
public class ReadingController {
    /**
     * Service handling reading-related operations.
     */
    private final ReadingService readingService;

    /**
     * Retrieves all readings associated with a particular sensor.
     *
     * @param sensorId Sensor ID
     * @return ResponseEntity containing a set of ReadingEntity associated with the sensor
     */
    @GetMapping("/{sensorId}")
    public ResponseEntity<Set<ReadingEntity>> getAllSensorReadings(@PathVariable Long sensorId) {
        return ResponseEntity.ok(readingService.getAllSensorReadings(sensorId));
    }

    /**
     * Retrieves all warnings associated with a specific plant.
     *
     * @param plantId Plant ID
     * @return ResponseEntity containing a set of ReadingEntity representing plant's warnings
     */
    @GetMapping("/warnings/{plantId}")
    public ResponseEntity<Set<ReadingEntity>> getAllPlantWarnings(@PathVariable Long plantId) {
        return ResponseEntity.ok(readingService.getAllPlantWarnings(plantId));
    }

    /**
     * Saves a new reading.
     *
     * @param readingDto Reading information to be saved
     * @return ResponseEntity containing a set of saved ReadingEntity and HTTP status CREATED
     */
    @PostMapping
    public ResponseEntity<Set<ReadingEntity>> saveReading(@RequestBody ReadingDto readingDto) {
        return new ResponseEntity<>(readingService.saveReading(readingDto), HttpStatus.CREATED);
    }
}
