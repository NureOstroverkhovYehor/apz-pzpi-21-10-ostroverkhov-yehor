package plantlightcycle.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import plantlightcycle.dto.ReadingDto;
import plantlightcycle.models.SensorEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Service for managing the creation and processing of sensor readings.
 */
@Service
@RequiredArgsConstructor
public class ReadingService {
    /**
     * Random instance for generating random values for sensor readings.
     */
    private final Random random = new Random();

    /**
     * Set to keep track of sensor IDs that have already been processed.
     */
    private final Set<Long> sensorsIds = new HashSet<>();

    /**
     * Service for managing light adjustments based on sensor readings.
     */
    private final LightService lightService;

    /**
     * RestTemplate for making HTTP requests to the backend API.
     */
    private final RestTemplate restTemplate;

    /**
     * Delay in seconds between generating new readings.
     */
    @Value("${newReadingSecondsDelay}")
    private int newReadingSecondsDelay;

    /**
     * Starts the process of creating sensor readings for the given list of sensors.
     *
     * @param sensors the list of sensors to process.
     */
    public void startCreatingReadings(List<SensorEntity> sensors) {
        List<SensorEntity> filteredSensors = filterAlreadyExistedSensors(sensors);
        filteredSensors.forEach(this::startSensorThread);
    }

    /**
     * Filters out sensors that have already been processed.
     *
     * @param sensors the list of sensors to filter.
     * @return a list of sensors that have not yet been processed.
     */
    private List<SensorEntity> filterAlreadyExistedSensors(List<SensorEntity> sensors) {
        List<SensorEntity> filteredSensors = sensors.stream()
                .filter(sensor -> !sensorsIds.contains(sensor.getId()))
                .toList();
        filteredSensors.forEach(sensor -> sensorsIds.add(sensor.getId()));
        return filteredSensors;
    }

    /**
     * Starts a new thread to process readings for a given sensor.
     *
     * @param sensor the sensor to process readings for.
     */
    private void startSensorThread(SensorEntity sensor) {
        new Thread(() -> {
            System.out.println("Added new sensor: " + sensor.getName());
            while (true) {
                sendNewReading(sensor);
                waitFor(newReadingSecondsDelay);
            }
        }).start();
    }

    /**
     * Creates and sends new reading.
     *
     * @param sensor the sensor to create readings for.
     */
    private void sendNewReading(SensorEntity sensor) {
        System.out.println("_________________________________________________________________________________");
        int value = generateRandomValue(sensor);
        ReadingDto readingDto = createReadingDto(sensor, value);

        System.out.println(readingDto);

        sendReading(readingDto);

        if (readingDto.isWarning()) {
            lightService.changeLightCorrespondingToWarning(value, sensor);
        }

        System.out.println("_________________________________________________________________________________");
    }

    /**
     * Generates a random value for a given sensor within its appropriate range.
     *
     * @param sensor the sensor to generate a value for a reading.
     * @return a random value within the sensor's range.
     */
    private int generateRandomValue(SensorEntity sensor) {
        int maxRandomValue = sensor.getMaxAppropriateValue() + sensor.getMinAppropriateValue();
        return random.nextInt(maxRandomValue);
    }

    /**
     * Creates a ReadingDto object for a given sensor and value.
     *
     * @param sensor the sensor to create a reading for.
     * @param value  the value of the reading.
     * @return a ReadingDto object containing the sensor's reading.
     */
    private ReadingDto createReadingDto(SensorEntity sensor, int value) {
        boolean isWarning = value > sensor.getMaxAppropriateValue() || value < sensor.getMinAppropriateValue();
        return new ReadingDto(sensor.getId(), sensor.getName(), value, isWarning);
    }

    /**
     * Sends a sensor reading to the backend API.
     *
     * @param readingDto the reading to send.
     */
    private void sendReading(ReadingDto readingDto) {
        HttpEntity<ReadingDto> request = new HttpEntity<>(readingDto);
        restTemplate.postForEntity("http://localhost:8080/readings", request, String.class);
        System.out.println("Reading sent successfully");
    }

    /**
     * Pauses the current thread for a specified number of seconds.
     *
     * @param seconds the number of seconds to wait.
     */
    public void waitFor(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}