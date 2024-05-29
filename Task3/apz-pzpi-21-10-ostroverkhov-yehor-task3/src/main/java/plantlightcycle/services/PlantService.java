package plantlightcycle.services;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import plantlightcycle.models.PlantEntity;
import plantlightcycle.models.SensorEntity;

import java.util.List;

/**
 * Service for managing plant and sensor data.
 */
@Service
@RequiredArgsConstructor
public class PlantService {
    /**
     * RestTemplate for making HTTP requests to the backend API.
     */
    private final RestTemplate restTemplate;

    /**
     * Retrieves a list of plants from the backend API.
     *
     * @return a list of PlantEntity objects.
     */
    public List<PlantEntity> receivePlantsFromBack() {
        ResponseEntity<List<PlantEntity>> response = restTemplate.exchange(
                "http://localhost:8080/plants",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        return response.getBody();
    }

    /**
     * Retrieves all sensors from the list of plants and sets their corresponding light levels.
     *
     * @param plants the list of PlantEntity objects containing sensor data.
     * @return a list of SensorEntity objects.
     */
    public List<SensorEntity> getAllSensorsFromPlants(List<PlantEntity> plants) {
        plants.forEach(plant -> plant.getSensors().forEach(sensor -> {
            sensor.setPlant(plant);
            sensor.setMinLightLevel(plant.getMinLightLevel());
            sensor.setMaxLightLevel(plant.getMaxLightLevel());
        }));
        return plants.stream().flatMap(plant -> plant.getSensors().stream()).toList();
    }
}
