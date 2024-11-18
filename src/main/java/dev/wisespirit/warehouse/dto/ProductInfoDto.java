package dev.wisespirit.warehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductInfoDto {
    private String description;
    private double salesPrice;
    private double cost;
    private int sku;
}
