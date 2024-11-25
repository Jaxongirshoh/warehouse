package dev.wisespirit.warehouse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "goods_receipt_note")
public class GoodsReceiptNote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "purchase_order_id")
    private PurchaseOrder purchaseOrder;
    @OneToMany(mappedBy = "goodsReceiptNote", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Column(name = "goods_receipt_note_lines")
    @Builder.Default
    private List<GoodsReceiptNoteLine> goodsReceiptNoteLines = new ArrayList<>();
    @Column(name = "date")
    @Builder.Default
    private LocalDate date = LocalDate.now();
    @Builder.Default
    private GrnStatus status = GrnStatus.PENDING;
    public int getTotalQty() {
        int total = 0;
        for (GoodsReceiptNoteLine goodsReceiptNoteLine : goodsReceiptNoteLines) 
            total += goodsReceiptNoteLine.getQty();
        return total;
    }

    public enum GrnStatus {
        PENDING,
        FULFILLED
    }

}