package dev.wisespirit.warehouse.entity;

import dev.wisespirit.warehouse.entity.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "goods_receipt_note_line")
public class GoodsReceiptNoteLine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "goods_receipt_note_id") // Foreign key to goods receipt note entity
    private GoodsReceiptNote goodsReceiptNote;
    @Column(name = "qty")
    private int qty;
    @ManyToOne
    @JoinColumn(name = "product_id") // Map foreign key in product entity
    private Product product;
    @Column(name = "notes")
    private String notes;

}
