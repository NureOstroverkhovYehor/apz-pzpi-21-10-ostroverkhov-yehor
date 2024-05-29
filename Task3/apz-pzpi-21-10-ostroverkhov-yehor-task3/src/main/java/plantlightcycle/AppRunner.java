package plantlightcycle;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import plantlightcycle.models.PlantEntity;
import plantlightcycle.models.SensorEntity;
import plantlightcycle.services.PlantService;
import plantlightcycle.services.ReadingService;

import java.util.List;

/**
 * Component that runs a background thread to update sensor readings based on plant data.
 */
@Component
@RequiredArgsConstructor
public class AppRunner implements CommandLineRunner {
    /**
     * Service for managing plant data.
     */
    private final PlantService plantService;

    /**
     * Service for managing the creation and processing of sensor readings.
     */
    private final ReadingService readingService;

    /**
     * The delay in seconds between updating sensors data.
     */
    @Value("${updateSensorsSeconds}")
    private int updateSensorsSeconds;

    /**
     * Starts a background thread to continuously update sensor readings.
     *
     * @param args the command line arguments (not used).
     */
    @Override
    public void run(String... args) {
        Thread thread = new Thread(() -> {
            while (true) {
                List<PlantEntity> plants = plantService.receivePlantsFromBack();
                List<SensorEntity> sensors = plantService.getAllSensorsFromPlants(plants);
                readingService.startCreatingReadings(sensors);
                readingService.waitFor(updateSensorsSeconds);
            }
        });

        thread.start();
    }
}
