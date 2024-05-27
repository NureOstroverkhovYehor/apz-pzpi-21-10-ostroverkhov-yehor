package plantlightcycle.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import plantlightcycle.dto.DeletionLampDto;
import plantlightcycle.dto.LampDto;
import plantlightcycle.dto.LampEditingDto;
import plantlightcycle.model.LampEntity;
import plantlightcycle.service.LampService;

import java.util.List;
import java.util.Set;

/**
 * REST controller managing operations related to lamps.
 * Endpoints for retrieving, adding, editing, and removing lamps.
 */
@RestController
@RequestMapping("/lamps")
@RequiredArgsConstructor
public class LampController {
    /**
     * Service handling lamp-related operations.
     */
    private final LampService lampService;

    /**
     * Endpoint to retrieve all lamps.
     *
     * @return ResponseEntity containing the list of all lamps
     */
    @GetMapping
    public ResponseEntity<List<LampEntity>> getAllLamps() {
        return ResponseEntity.ok(lampService.getAllLamps());
    }

    /**
     * Endpoint to retrieve all lamps associated with a specific sensor.
     *
     * @param sensorId The ID of the sensor
     * @return ResponseEntity containing the list of lamps associated with the sensor
     */
    @GetMapping("/{sensorId}")
    public ResponseEntity<List<LampEntity>> getAllSensorLamps(@PathVariable Long sensorId) {
        return ResponseEntity.ok(lampService.getAllSensorLamps(sensorId));
    }

    /**
     * Endpoint to add a new lamp to a sensor.
     *
     * @param lampDto The LampDto object representing the new lamp
     * @return ResponseEntity containing the set of lamps after addition
     */
    @PostMapping
    public ResponseEntity<Set<LampEntity>> addLampToSensor(@RequestBody LampDto lampDto) {
        return new ResponseEntity<>(lampService.saveLamp(lampDto), HttpStatus.CREATED);
    }

    /**
     * Endpoint to change the light level of a lamp.
     *
     * @param lampEditingDto The LampEditingDto object representing the changes
     * @return ResponseEntity containing the set of lamps after the light level change
     */
    @PatchMapping
    public ResponseEntity<Set<LampEntity>> changeLightLevelOfLamp(@RequestBody LampEditingDto lampEditingDto) {
        return new ResponseEntity<>(lampService.editLamp(lampEditingDto), HttpStatus.CREATED);
    }

    /**
     * Endpoint to remove a lamp from a sensor.
     *
     * @param deletionLampDto The DeletionLampDto object representing the lamp to be removed
     * @return ResponseEntity indicating the success of the removal operation
     */
    @DeleteMapping
    public ResponseEntity<Set<LampEntity>> removeLampFromSensor(@RequestBody DeletionLampDto deletionLampDto) {
        lampService.removeLampFromSensor(deletionLampDto);
        return ResponseEntity.noContent().build();
    }
}
