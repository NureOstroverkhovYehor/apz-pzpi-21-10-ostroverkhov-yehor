package plantlightcycle.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import plantlightcycle.dto.GrowthPercentageEditingDto;
import plantlightcycle.dto.PlantDto;
import plantlightcycle.model.PlantEntity;
import plantlightcycle.repository.PlantRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlantService {
    private final PlantRepository plantRepository;

    public List<PlantEntity> getAllPlants() {
        return plantRepository.findAll();
    }

    public PlantEntity savePlantFromDto(PlantDto plantDto) {
        return save(new PlantEntity(
                plantDto.name(),
                plantDto.minLightLevel(),
                plantDto.maxLightLevel(),
                plantDto.growthPercentage()));
    }

    public PlantEntity changeGrowthPercentage(GrowthPercentageEditingDto growPercEditDto) {
        PlantEntity plant = getById(growPercEditDto.plantId());

        plant.setGrowthPercentage(growPercEditDto.newPercentageValue());

        return plantRepository.save(plant);
    }

    public PlantEntity getById(Long plantId) {
        return plantRepository.findById(plantId).orElseThrow(EntityNotFoundException::new);
    }

    public void deleteById(Long plantId) {
        plantRepository.deleteById(plantId);
    }

    public PlantEntity save(PlantEntity plant) {
        return plantRepository.save(plant);
    }
}
