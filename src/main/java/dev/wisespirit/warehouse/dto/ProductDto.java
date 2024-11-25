package dev.wisespirit.warehouse.dto;

import dev.wisespirit.warehouse.entity.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto extends BaseDto {
    private String name;
    private Long contractorId;
    private int sku;
    private String description;
    private ProductPriceDto productPrice;
    private ProductCostDto productCostDto;
    public Product toProduct() {
        return Product.builder()
                .sku(sku)
                .description(description)
                .build();
    }
}
