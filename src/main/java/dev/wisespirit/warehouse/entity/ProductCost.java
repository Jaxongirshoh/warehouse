package dev.wisespirit.warehouse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "product_price")
public class ProductCost {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_price_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @OneToMany(mappedBy = "itemPrice", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Column(name = "sales_order_line_id")
    private List<SalesOrderLine> salesOrderLine = new ArrayList<>();
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
