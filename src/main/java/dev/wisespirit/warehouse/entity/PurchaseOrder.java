package dev.wisespirit.warehouse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "purchase_order")
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "date")
    @Builder.Default
    private LocalDate date = LocalDate.now();
    @ManyToOne
    @JoinColumn(name = "contractor_id")
    private Contractor contractor;
    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Column(name = "goods_receipt_notes")
    @Builder.Default
    private List<GoodsReceiptNote> goodsReceiptNotes = new ArrayList<>();
    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Column(name = "purchase_order_lines")
    @Builder.Default
    private List<PurchaseOrderLine> purchaseOrderLines = new ArrayList<>();
    @Column(name = "status")
    @Builder.Default
    private PoStatus status = PoStatus.IN_TRANSIT;

    public double getTotal() {
        double total = 0;
        for (int i = 0; i < purchaseOrderLines.size(); i++) {
            total += purchaseOrderLines.get(i).getProductCost().getCost() * purchaseOrderLines.get(i).getQty();
        }
        return total;
    }

    public enum PoStatus {
        IN_TRANSIT(0),
        RECEIVED(1),
        PARTIALLY_RECEIVED(2);

        private final int pk;

        PoStatus(int pk) {
            this.pk = pk;
        }

        public int getPk() {
            return pk;
        }
    }
}
