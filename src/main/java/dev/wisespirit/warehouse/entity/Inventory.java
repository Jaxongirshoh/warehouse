package dev.wisespirit.warehouse.entity;

import dev.wisespirit.warehouse.entity.auth.BaseAuditable;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class represents the inventory information for a product.
 *
 * This class extends `BaseAuditable` and provides fields for tracking inventory details:
 * - **stockQuantity:** The current quantity of the product in stock.
 * - **product:** The product associated with this inventory record.
 *
 * @author wisespirit
 * @version 0.1
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "inventories")
public class Inventory extends BaseAuditable {
    private Integer stockQuantity;
    @OneToOne
    private Product product;
}
