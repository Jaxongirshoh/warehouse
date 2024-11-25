package dev.wisespirit.warehouse.repository;

import dev.wisespirit.warehouse.entity.product.ProductPrice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductCostRepository {
    @Query(value = """
        SELECT\s
        ip.price\s
        FROM product_cost ic\s
        WHERE ic.product_id = :productId\s
        AND ic.start_date <= CURRENT_DATE()\s
        AND ic.end_date >= CURRENT_DATE()\
        """, nativeQuery = true)
    public double findCurrentPriceByItemId(@Param("itemId") Long id);

    @Query(value = """
        SELECT * FROM product_cost ic\s
        WHERE ic.product_id = :productId\s
        AND ic.start_date <= CURRENT_DATE()\s
        AND ic.end_date >= CURRENT_DATE()\
        """, nativeQuery = true)
    ProductPrice findCurrentItemPriceByItemId(@Param("itemId") Long id);
}
