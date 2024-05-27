package plantlightcycle.dto;

/**
 * Represents a data transfer object (DTO) for creating or updating a lamp.
 * Includes information about the sensor ID, name, light level and spectrum of the lamp.
 */
public record LampDto(Long sensorId, String name, Integer lightLevel, String spectrum) {
    // Record fields and constructor implicitly defined
}
