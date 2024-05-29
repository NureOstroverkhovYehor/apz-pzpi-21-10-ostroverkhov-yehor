package plantlightcycle.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Represents a reading entity captured by a sensor.
 * This class defines attributes and methods related to readings.
 */
@Getter
@Setter
@NoArgsConstructor
public class ReadingEntity {
    /**
     * Unique identifier for the reading entity.
     */
    private Long id;

    /**
     * The sensor associated with this reading.
     */
    @JsonIgnore
    private SensorEntity sensor;

    /**
     * Name associated with the reading.
     */
    private String name;

    /**
     * Value of the reading.
     */
    private Integer value;

    /**
     * Date and time when the reading was captured.
     */
    private LocalDateTime dateTime;

    /**
     * Indicates whether the reading is a warning.
     */
    private Boolean isWarning;

    /**
     * Returns a string representation of the ReadingEntity.
     *
     * @return a formatted string containing the reading's details
     */
    @Override
    public String toString() {
        return "\n\n\tReadingEntity\n" +
                "\t\tid = " + id + ",\n" +
                "\t\tname = '" + name + "',\n" +
                "\t\tvalue = " + value + ",\n" +
                "\t\tdateTime = " + dateTime + ",\n" +
                "\t\tisWarning = " + isWarning + "\n";
    }
}