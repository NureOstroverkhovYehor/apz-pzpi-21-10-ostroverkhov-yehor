package plantlightcycle.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a lamp entity within the system.
 * Each lamp can be associated with a sensor and contains information about its name and light level.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class LampEntity {
    /**
     * Unique identifier for the lamp entity.
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * The sensor associated with this reading.
     */
    @ManyToOne
    @JsonIgnore
    private SensorEntity sensor;

    /**
     * The name of the lamp.
     */
    private String name;

    /**
     * The light level of the lamp.
     */
    private Integer lightLevel;

    /**
     * The spectrum of the lamp.
     * Can be Full, Blue, Red, Far Red
     */
    private String spectrum;

    /**
     * Constructs a LampEntity object with a name and light level.
     *
     * @param name       The name of the lamp.
     * @param lightLevel The light level of the lamp.
     * @param spectrum   The spectrum of the lamp.
     */
    public LampEntity(String name, Integer lightLevel, String spectrum) {
        this.name = name;
        this.lightLevel = lightLevel;
        this.spectrum = spectrum;
    }
}
