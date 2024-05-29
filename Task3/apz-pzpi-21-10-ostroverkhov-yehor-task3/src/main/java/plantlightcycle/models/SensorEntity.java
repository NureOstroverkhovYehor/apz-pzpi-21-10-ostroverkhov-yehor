package plantlightcycle.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 * Represents a sensor entity used within the system.
 * This class defines attributes and behaviors of a sensor.
 */
@Getter
@Setter
@NoArgsConstructor
public class SensorEntity {
    /**
     * Unique identifier for the sensor entity.
     */
    private Long id;

    /**
     * Name of the sensor.
     */
    private String name;

    /**
     * The plant associated with this sensor.
     * An instance of PlantEntity.
     */
    private PlantEntity plant;

    /**
     * Min light level for plant.
     */
    private int minLightLevel;

    /**
     * Max light level for plant.
     */
    private int maxLightLevel;

    /**
     * Min appropriate value.
     */
    private int minAppropriateValue;

    /**
     * Max appropriate value.
     */
    private int maxAppropriateValue;

    /**
     * Set of readings associated with this sensor.
     * These readings are related to this sensor.
     */
    private Set<ReadingEntity> readings;

    /**
     * Set of lamps associated with this sensor.
     * These lamps are related to this sensor.
     */
    private Set<LampEntity> lamps;

    /**
     * Returns a string representation of the SensorEntity.
     *
     * @return a formatted string containing the sensor's details
     */
    @Override
    public String toString() {
        return "\n\nSensorEntity\n" +
                "\tid = " + id + ",\n" +
                "\tname = '" + name + "',\n" +
                "\tminLightLevel = '" + minLightLevel + "',\n" +
                "\tmaxLightLevel = '" + maxLightLevel + "',\n" +
                "\tplant = " + (plant != null ? plant.getName() : "null") + ",\n" +
                "\treadings = " + readings + ",\n" +
                "\tlamps = " + lamps + "\n";
    }
}