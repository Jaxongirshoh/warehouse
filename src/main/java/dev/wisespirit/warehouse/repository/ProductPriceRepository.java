package dev.wisespirit.warehouse.repository;

import dev.wisespirit.warehouse.entity.product.ProductPrice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductPriceRepository {
    @Query(value = """
        SELECT\s
        ip.price\s
        FROM product_price ip\s
        WHERE ip.product_id = :productId\s
        AND ip.start_date <= CURRENT_DATE()\s
        AND ip.end_date >= CURRENT_DATE()\
        """, nativeQuery = true)
    public double findCurrentPriceByItemId(@Param("itemId") Long id);

    @Query(value = """
        SELECT * FROM product_price ip\s
        WHERE ip.product_id = :itemId\s
        AND ip.start_date <= CURRENT_DATE()\s
        AND ip.end_date >= CURRENT_DATE()\
        """, nativeQuery = true)
    ProductPrice findCurrentItemPriceByItemId(@Param("itemId") Long id);
}
