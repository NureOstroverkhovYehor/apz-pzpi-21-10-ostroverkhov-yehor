package plantlightcycle.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 * Represents a plant entity on the farm.
 * This class defines attributes and methods related to plants.
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
public class PlantEntity {
    /**
     * Unique identifier for the plant entity.
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * Name associated with the plant.
     */
    private String name;

    /**
     * The minimum light level required by the plant.
     * Must be between 0 and 100.
     */
    @Min(0)
    @Max(100)
    private Integer minLightLevel;

    /**
     * The maximum light level the plant can tolerate.
     * Must be between 0 and 100.
     */
    @Min(0)
    @Max(100)
    private Integer maxLightLevel;

    /**
     * The growth percentage of the plant.
     * Must be between 0 and 100.
     */
    @Min(0)
    @Max(100)
    private Integer growthPercentage;

    /**
     * Set of sensors associated with the plant.
     */
    @OneToMany(mappedBy = "plant", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SensorEntity> sensors;

    /**
     * Constructs a PlantEntity object with a name, minLightLevel, maxLightLevel and growthPercentage.
     *
     * @param name The plant's name.
     * @param minLightLevel The plant's minimum light level.
     * @param maxLightLevel The plant's maximum light level.
     * @param growthPercentage The plant's growth percentage, by default 0.
     */
    public PlantEntity(String name, int minLightLevel, int maxLightLevel, int growthPercentage) {
        this.name = name;
        this.minLightLevel = minLightLevel;
        this.maxLightLevel = maxLightLevel;
        this.growthPercentage = growthPercentage;
        this.sensors = Set.of();
    }

    /**
     * Adds a sensor to the plant's set of sensors.
     *
     * @param sensor The sensor to be added.
     */
    public void addSensor(SensorEntity sensor) {
        sensors.add(sensor);
    }

    /**
     * Removes a sensor from the plant's set of sensors.
     *
     * @param sensor The sensor to be removed.
     */
    public void removeSensor(SensorEntity sensor) {
        sensors.remove(sensor);
    }
}
