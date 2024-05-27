package plantlightcycle.dto;

/**
 * Represents a Data Transfer Object (DTO) for lamp deletion requests.
 * <p>
 * This record encapsulates information required for deleting a lamp,
 * specifically containing the sensor id and the name of the lamp to be deleted.
 * <p>
 * Fields:
 * - sensorId: Long representing the sensor's id associated with the lamp
 * - name: String representing the name of the lamp to be deleted
 */
public record DeletionLampDto(Long sensorId, String name) {
    // No need for explicit constructor, accessor methods, equals, hashCode, or toString
    // The record implicitly provides these based on its components (sensorId and name)
}
