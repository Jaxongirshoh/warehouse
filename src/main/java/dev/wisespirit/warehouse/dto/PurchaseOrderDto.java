package dev.wisespirit.warehouse.dto;

import java.time.LocalDate;

public interface PurchaseOrderDto {
    Long getId();
    Integer getStatus();
    LocalDate getDate();
    Integer getPurchaseOrder();
    Double getTotal();
}
