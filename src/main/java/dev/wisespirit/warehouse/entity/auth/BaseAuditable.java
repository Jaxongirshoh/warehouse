package dev.wisespirit.warehouse.entity.auth;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;

/**
 * Base class for all auditable entities.
 * - **id:** Unique identifier for the entity.
 * - **createdAt:** Date and time of entity creation.
 * - **createdBy:** ID of the user who created the entity.
 * - **updatedAt:** Date and time of the last modification.
 * - **updatedBy:** ID of the user who last modified the entity.
 * @author wisespirit
 * @version 0.1
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@Deprecated
public abstract class BaseAuditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreatedDate()
    private LocalDate createdAt;
    @CreatedBy()
    private Long createdBy;
    @LastModifiedDate()
    private LocalDate updatedAt;
    @LastModifiedBy()
    private Long updatedBy;
}
