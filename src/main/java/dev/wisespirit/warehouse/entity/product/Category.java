package dev.wisespirit.warehouse.entity.product;

import dev.wisespirit.warehouse.entity.auth.BaseAuditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * This class represents a category of products.
 *
 *   This class extends `BaseAuditable` and provides fields for defining categories and their relationships with products:
 *   - **name:** The name of the category.
 *   - **parentId:** The ID of the parent category, if any.
 *   - **products:** A set of products associated with this category.
 *
 * @author wisespirit
 * @version 0.1
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category extends BaseAuditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotBlank(message = "name cannot be blank")
    private String name;
}
