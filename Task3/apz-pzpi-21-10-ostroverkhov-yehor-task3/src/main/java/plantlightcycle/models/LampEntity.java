package plantlightcycle.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a lamp entity within the system.
 * Each lamp can be associated with a sensor and contains information about its name, light level and spectrum.
 */
@Getter
@Setter
@NoArgsConstructor
public class LampEntity {
    /**
     * Unique identifier for the lamp entity.
     */
    private Long id;

    /**
     * The sensor associated with this reading.
     */
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
     * Returns a string representation of the LampEntity.
     *
     * @return a formatted string containing the lamp's details
     */
    @Override
    public String toString() {
        return "\n\n\tLampEntity\n" +
                "\t\tid = " + id + ",\n" +
                "\t\tname = '" + name + "',\n" +
                "\t\tlightLevel = " + lightLevel + ",\n" +
                "\t\tspectrum = '" + spectrum + "'\n";
    }
}