package dev.wisespirit.warehouse.entity;

import dev.wisespirit.warehouse.entity.auth.AuthUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="sales_order")
public class SalesOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "date")
    @Builder.Default
    private LocalDate date = LocalDate.now();
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private AuthUser customer;
    @OneToMany(mappedBy = "salesOrder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Column(name = "picking_jobs")
    @Builder.Default
    private List<PickingJob> pickingJobs = new ArrayList<>();
    @OneToMany(mappedBy = "salesOrder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Column(name = "sales_order_lines")
    @Builder.Default
    private List<SalesOrderLine> saleOrderLines = new ArrayList<>();
    @Column(name = "status")
    @Builder.Default
    private SoStatus status = SoStatus.PENDING;

    public double getTotal() {
        double total = 0;
        for (int i = 0; i < saleOrderLines.size(); i++) {
            total += saleOrderLines.get(i).getSubtotal();
        }
        return total;
    }

    public enum SoStatus {
        PENDING,
        PARTIALLY_SHIPPED,
        SHIPPED
    }
}
