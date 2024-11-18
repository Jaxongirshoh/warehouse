package dev.wisespirit.warehouse.entity;

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
    @JoinColumn(name = "product_price_id", nullable = false)
    private ProductPrice productPrice;

    /**
     * Calculate subtotal based on quantity and item price.
     *
     * @return subtotal value
     */
    public double getSubtotal() {
        return qty * (productPrice != null ? productPrice.getPrice() : 0.0);
    }
}

