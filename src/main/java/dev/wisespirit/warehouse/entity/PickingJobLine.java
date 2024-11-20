package dev.wisespirit.warehouse.entity;

import dev.wisespirit.warehouse.entity.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "picking_job_line")
public class PickingJobLine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "picking_job_id")
    private PickingJob pickingJob;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "qty_to_pick")
    private int qtyToPick;
    @Column(name = "qty_picked")
    private int qtyPicked;
}
