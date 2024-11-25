package dev.wisespirit.warehouse.entity.product;

import dev.wisespirit.warehouse.entity.SalesOrderLine;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "product_cost")
public class ProductCost {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_cost_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "productCost", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SalesOrderLine> salesOrderLines = new ArrayList<>();

    @Column(name = "start_date")
    private LocalDate start;

    @Column(name = "end_date")
    private LocalDate end;

    @Column(name = "cost")
    private double cost;

    public String formattedEndDate() {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return end.format(pattern);
    }
}
