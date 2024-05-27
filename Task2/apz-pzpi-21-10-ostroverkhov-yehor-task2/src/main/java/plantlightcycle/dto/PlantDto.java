package plantlightcycle.dto;

/**
 * Represents a data transfer object (DTO) for creating a plant.
 * Includes information about name, min light level, max light level and growth percentage of the plant.
 */
public record PlantDto(String name, int minLightLevel, int maxLightLevel, int growthPercentage) {
}
