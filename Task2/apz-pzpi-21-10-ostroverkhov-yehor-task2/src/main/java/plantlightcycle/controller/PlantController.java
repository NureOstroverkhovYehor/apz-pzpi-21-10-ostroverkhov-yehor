package plantlightcycle.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import plantlightcycle.dto.GrowthPercentageEditingDto;
import plantlightcycle.dto.PlantDto;
import plantlightcycle.model.PlantEntity;
import plantlightcycle.service.PlantService;

import java.util.List;

@RestController
@RequestMapping("/plants")
@RequiredArgsConstructor
public class PlantController {
    private final PlantService plantService;

    @GetMapping
    public ResponseEntity<List<PlantEntity>> getAllPlants() {
        return ResponseEntity.ok(plantService.getAllPlants());
    }

    @PostMapping
    public ResponseEntity<PlantEntity> createPlant(@RequestBody PlantDto plantDto) {
        return new ResponseEntity<>(plantService.savePlantFromDto(plantDto), HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<PlantEntity> changeGrowthPercentage(@RequestBody GrowthPercentageEditingDto growPercEditDto) {
        return ResponseEntity.ok(plantService.changeGrowthPercentage(growPercEditDto));
    }

    @DeleteMapping("/{plantId}")
    public ResponseEntity<List<PlantEntity>> deletePlantByLogin(@PathVariable Long plantId) {
        plantService.deleteById(plantId);

        return ResponseEntity.ok(plantService.getAllPlants());
    }
}
