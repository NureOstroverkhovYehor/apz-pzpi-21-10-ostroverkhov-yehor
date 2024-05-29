package plantlightcycle.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import plantlightcycle.dto.LampEditAllDto;
import plantlightcycle.models.SensorEntity;

/**
 * Service for managing light levels based on sensor readings.
 */
@Service
@RequiredArgsConstructor
public class LightService {
    /**
     * RestTemplate for making HTTP requests to the backend API.
     */
    private final RestTemplate restTemplate;

    /**
     * Adjusts the light level based on the sensor reading value.
     * If the value is outside the appropriate range, the light level is changed.
     *
     * @param value  The current reading value from the sensor.
     * @param sensor The sensor entity containing information about the sensor and light levels.
     */
    public void changeLightCorrespondingToWarning(int value, SensorEntity sensor) {
        int newLightLevel;
        if (value > sensor.getMaxAppropriateValue()) {
            newLightLevel = sensor.getMaxLightLevel();
        } else {
            newLightLevel = sensor.getMinLightLevel();
        }
        HttpEntity<LampEditAllDto> request = new HttpEntity<>(new LampEditAllDto(sensor.getId(), newLightLevel));
        restTemplate.postForEntity("http://localhost:8080/lamps/all", request, String.class);
        System.out.println("Lamp change dto sent successfully");
    }
}