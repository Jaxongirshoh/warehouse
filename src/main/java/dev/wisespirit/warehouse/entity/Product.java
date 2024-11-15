package dev.wisespirit.warehouse.entity;

import dev.wisespirit.warehouse.entity.auth.BaseAuditable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

/**
 *This class represents a product within the system.
 *
 * This class extends `BaseAuditable` and provides fields for defining products and their attributes:
 * - **name:** The name of the product.
 * - **description:** A description of the product.
 * - **price:** The price of the product.
 * - **inventory:** The inventory information associated with the product.
 * - **categories:** A set of categories to which the product belongs.
 *
 * @author wisespirit
 * @version 0.1
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product extends BaseAuditable {
    @Column(nullable = false)
    @NotBlank(message = "name cannot be blank")
    private String name;
    private String description;
    @Column(nullable = false)
    @NotBlank(message = "price cannot be blank")
    private double price;
    private long quantity;
    @ManyToMany()
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id",referencedColumnName = "id")
    )
    private Set<Category> categories;
}
