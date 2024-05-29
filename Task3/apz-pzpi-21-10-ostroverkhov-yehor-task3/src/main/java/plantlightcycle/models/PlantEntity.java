package plantlightcycle.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 * Represents a plant entity on the farm.
 * This class defines attributes and methods related to plants.
 */
@NoArgsConstructor
@Getter
@Setter
public class PlantEntity {
    /**
     * Unique identifier for the plant entity.
     */
    private Long id;

    /**
     * Name associated with the plant.
     */
    private String name;

    /**
     * The minimum light level required by the plant.
     */
    private Integer minLightLevel;

    /**
     * The maximum light level the plant can tolerate.
     */
    private Integer maxLightLevel;

    /**
     * The growth percentage of the plant.
     * Must be between 0 and 100.
     */
    private Integer growthPercentage;

    /**
     * Set of sensors associated with the plant.
     */
    private Set<SensorEntity> sensors;

    /**
     * Returns a string representation of the PlantEntity.
     *
     * @return a formatted string containing the plant's details
     */
    @Override
    public String toString() {
        return "PlantEntity\n" +
                "    id = " + id + ",\n" +
                "    name = '" + name + "',\n" +
                "    minLightLevel = " + minLightLevel + ",\n" +
                "    maxLightLevel = " + maxLightLevel + ",\n" +
                "    growthPercentage = " + growthPercentage + ",\n" +
                "    sensors = " + sensors + "\n";
    }
}