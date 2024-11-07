package dev.wisespirit.warehouse.entity;

import dev.wisespirit.warehouse.entity.auth.BaseAuditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class represents a cash box within a warehouse.

 * This class extends `BaseAuditable` and provides fields for tracking cash box details:
 * - **name:** The name of the cash box.
 * - **income:** The total income recorded in the cash box.
 * - **expense:** The total expense recorded in the cash box.
 * - **total:** The total amount of money in the cash box.
 * - **balance:** The current balance of the cash box.
 * - **warehouse:** The warehouse to which the cash box belongs.
 *
 * @author wisespirit
 * @version 0.1
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cashboxes")
public class CashBox extends BaseAuditable {
    @Column(nullable = false,unique = true)
    private String name;
    @Column(nullable = false)
    @NotBlank(message = "income cannot be blank")
    private double income;
    @Column(nullable = false)
    @NotBlank(message = "expense cannot be blank")
    private double expense;
    @Column(nullable = false)
    @NotBlank(message = "total cannot be blank")
    private double total;
    @Column(nullable = false)
    @NotBlank(message = "balance cannot be blank")
    private double balance;
    @ManyToOne()
    @JoinColumn(name = "warehouse_id",nullable = false)
    private Warehouse warehouse;
}
