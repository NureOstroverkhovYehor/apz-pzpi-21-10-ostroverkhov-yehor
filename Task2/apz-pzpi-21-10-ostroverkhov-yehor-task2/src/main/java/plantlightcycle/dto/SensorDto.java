package plantlightcycle.dto;

/**
 * Represents a Data Transfer Object (DTO) for sensor information.
 * <p>
 * This record encapsulates information about a sensor, typically used
 * for creating or updating a sensor, containing the plant ID and the name of the sensor.
 * <p>
 * Fields:
 * - plantId: Long representing the plant's ID associated with the sensor
 * - name: String representing the name of the sensor
 */
public record SensorDto(Long plantId, String name) {
    // No need for explicit constructor, accessor methods, equals, hashCode, or toString
    // The record implicitly provides these based on its components (userLogin and name)
}
