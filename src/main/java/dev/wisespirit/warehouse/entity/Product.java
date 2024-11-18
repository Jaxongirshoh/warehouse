package dev.wisespirit.warehouse.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a product within the system.
 * <p>
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
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "contractor_id")
    private Contractor contractor;

    @Column(name = "sku")
    private int sku;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<ProductPrice> prices = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<ProductCost> costs = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<GoodsReceiptNoteLine> goodsReceiptNoteLines = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<SalesOrderLine> salesOrderLines = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<PurchaseOrderLine> purchaseOrderLines = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<PickingJobLine> pickingJobLines = new ArrayList<>();

}



