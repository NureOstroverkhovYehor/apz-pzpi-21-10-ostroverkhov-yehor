package plantlightcycle.dto;

/**
 * A data transfer object (DTO) representing the request to edit all lamps.
 * Contains the sensor ID of the lamp and the new value to set.
 */
public record LampEditAllDto(Long sensorId, Integer newValue) {
    // No constructor needed, record automatically provides a constructor
    // Parameters:
    // - sensorId: the sensor ID of the lamp to edit
    // - newValue: the new value to set for the lamp
}