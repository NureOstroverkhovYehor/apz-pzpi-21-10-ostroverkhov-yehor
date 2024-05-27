package plantlightcycle.dto;

/**
 * Represents a Data Transfer Object (DTO) for sensor deletion requests.
 * <p>
 * This record encapsulates information required for deleting a sensor,
 * specifically containing the plant ID and the name of the sensor to be deleted.
 * <p>
 * Fields:
 * - plantId: Long representing the plant's ID associated with the sensor
 * - name: String representing the name of the sensor to be deleted
 */
public record DeletionSensorDto(Long plantId, String name) {
    // No need for explicit constructor, accessor methods, equals, hashCode, or toString
    // The record implicitly provides these based on its components (plantId and name)
}
