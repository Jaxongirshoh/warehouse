package dev.wisespirit.warehouse.dto;

import dev.wisespirit.warehouse.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long contractorId;
    private int sku;
    private String description;
    public Product toProduct() {
        return Product.builder()
                .sku(sku)
                .description(description)
                .build();
    }
}
