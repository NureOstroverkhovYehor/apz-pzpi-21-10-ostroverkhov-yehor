package plantlightcycle.dto;

/**
 * Represents a data transfer object (DTO) for editing the properties of a lamp.
 * Contains information about the sensor ID, lamp name, and the new value to be updated.
 */
public record LampEditingDto(Long sensorId, String name, Integer newValue) {
    // Record fields and constructor implicitly defined
}
