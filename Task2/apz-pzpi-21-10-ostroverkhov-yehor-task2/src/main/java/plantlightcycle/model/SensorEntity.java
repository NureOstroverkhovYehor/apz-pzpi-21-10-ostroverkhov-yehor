package plantlightcycle.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 * Represents a sensor entity used within the system.
 * This class defines attributes and behaviors of a sensor.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class SensorEntity {
    /**
     * Unique identifier for the sensor entity.
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * Name of the sensor.
     */
    private String name;

    /**
     * The plant associated with this sensor.
     * An instance of PlantEntity.
     */
    @ManyToOne
    @JsonIgnore
    private PlantEntity plant;

    /**
     * Set of readings associated with this sensor.
     * These readings are related to this sensor.
     */
    @OneToMany(mappedBy = "sensor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ReadingEntity> readings;

    /**
     * Set of lamps associated with this sensor.
     * These lamps are related to this sensor.
     */
    @OneToMany(mappedBy = "sensor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<LampEntity> lamps;

    /**
     * Constructs a SensorEntity object with a specified name.
     *
     * @param name The name of the sensor.
     */
    public SensorEntity(String name) {
        this.name = name;
        this.readings = Set.of();
        this.lamps = Set.of();
    }

    /**
     * Adds a reading entity to the set of readings associated with this sensor.
     *
     * @param reading The ReadingEntity to be added.
     */
    public void addReading(ReadingEntity reading) {
        readings.add(reading);
    }

    public void addLamp(LampEntity lamp) {
        lamps.add(lamp);
    }

    public void removeLamp(LampEntity lamp) {
        lamps.remove(lamp);
    }
}
