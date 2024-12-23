package dev.wisespirit.warehouse.entity;

import dev.wisespirit.warehouse.entity.product.Product;
import dev.wisespirit.warehouse.entity.product.ProductCost;
import dev.wisespirit.warehouse.entity.product.ProductPrice;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "sales_order_line")
public class SalesOrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "qty", nullable = false)
    private int qty;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sales_order_id", nullable = false)
    private SalesOrder salesOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_cost_id", nullable = false)
    private ProductCost productCost;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_price_id", nullable = false)
    private ProductPrice productPrice;


    public double getSubtotal() {
        return qty * (productCost != null ? productCost.getCost() : 0.0);
    }
}
