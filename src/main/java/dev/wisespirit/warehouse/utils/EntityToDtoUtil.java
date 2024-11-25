package dev.wisespirit.warehouse.utils;

import jakarta.validation.constraints.NotBlank;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class EntityToDtoUtil {

    private static final ModelMapper modelMapper = new ModelMapper();

    /**
     * Converts a single DTO to an entity.
     *
     * @param dto         The DTO to convert.
     * @param entityClass The class of the target entity.
     * @param <T>         The type of the entity.
     * @param <U>         The type of the DTO.
     * @return The converted entity.
     */
    public static <T, U> T convertToEntity(@NotBlank(message = "DTO cannot be blank") U dto, Class<T> entityClass) {
        if (dto == null) {
            throw new IllegalArgumentException("DTO cannot be null");
        }
        return modelMapper.map(dto, entityClass);
    }

    /**
     * Converts a list of entities to a list of DTOs.
     *
     * @param entities The list of entities to convert.
     * @param dtoClass The class of the target DTOs.
     * @param <T>      The type of the entity.
     * @param <U>      The type of the DTO.
     * @return A list of converted DTOs.
     */
    public static <T, U> List<U> convertToDtos(@NotBlank(message = "Entity cannot be blank") List<T> entities, Class<U> dtoClass) {
        if (entities == null || entities.isEmpty()) {
            throw new IllegalArgumentException("Entity list cannot be null or empty");
        }
        return entities.stream()
                .map(entity -> modelMapper.map(entity, dtoClass))
                .collect(Collectors.toList());
    }

    /**
     * Converts a list of DTOs to a list of entities.
     *
     * @param dtos        The list of DTOs to convert.
     * @param entityClass The class of the target entities.
     * @param <T>         The type of the entity.
     * @param <U>         The type of the DTO.
     * @return A list of converted entities.
     */
    public static <T, U> List<T> convertToEntities(@NotBlank(message = "DTO cannot be blank") List<U> dtos, Class<T> entityClass) {
        if (dtos == null || dtos.isEmpty()) {
            throw new IllegalArgumentException("DTO list cannot be null or empty");
        }
        return dtos.stream()
                .map(dto -> modelMapper.map(dto, entityClass))
                .collect(Collectors.toList());
    }
}
