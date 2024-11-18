package dev.wisespirit.warehouse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "purchase_order_line")
public class PurchaseOrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "purchase_order_id") //Foreign key mapp to po entity
    private PurchaseOrder purchaseOrder;
    @Column(name = "qty")
    private int qty;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "product_cost_id")
    private ProductCost productCost;
    public double getSubtotal() {
        return qty * productCost.getCost();
    }
}
