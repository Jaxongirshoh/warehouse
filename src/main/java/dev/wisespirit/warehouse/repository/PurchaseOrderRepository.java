package dev.wisespirit.warehouse.repository;

import dev.wisespirit.warehouse.dto.PurchaseOrderDto;
import dev.wisespirit.warehouse.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface PurchaseOrderRepository extends CrudRepository<PurchaseOrder, Long> {
    /**
     * Return a Purchase Order that matches the given purchase order number
     * @param purchaseOrderNumber
     * @return
     */
    @Query(value = "SELECT * FROM purchase_order WHERE purchase_order.purchase_order_number = :purchaseOrderNumber", nativeQuery = true)
    public Optional<PurchaseOrder> findByPurchaseOrderNumber(@Param("purchaseOrderNumber") int purchaseOrderNumber);

    /**
     * Return a list of all the purchase order by a given vendor
     * @param contractor
     * @return
     */
    @Query(value = """
        SELECT
            purchase_order.id AS "id",
            purchase_order.status AS "status",
            purchase_order.date AS "date", purchase_order.purchase_order_number AS "purchaseOrder",
            ROUND(SUM(purchase_order_line.qty * item_cost.cost),2) AS "total"
        FROM 
            vendor AS V
            INNER JOIN purchase_order ON purchase_order.contractor_id = V.id
            INNER JOIN purchase_order_line ON purchase_order_line.purchase_order_id = purchase_order.id
            INNER JOIN product_cost ON product_cost.id = purchase_order_line.product_cost_id
        WHERE 
            V.id = :contractor
        GROUP BY 
            purchase_order.purchase_order_number
        ORDER BY 
            purchase_order.date DESC
        """, nativeQuery = true)
    public List<PurchaseOrderDto> findAllByContractor(@Param("contractor") Long contractorId);
    /**
     * @Query(value = "SELECT * FROM PURCHASEORDER WHERE PURCHASEORDER.vendor = :vendor", nativeQuery = true)
    public Optional<PurchaseOrder> findAllByVendor(@Param("vendor") int vendorId);
     */

    /**
     * Returns a list of the purchase order persistedPurchaseOrderDto
     * PurchaseOrderNumberDto in the dba
     * @return
     */
    @Query(value = """
        SELECT
            po.id AS "id",
            po.status AS "status",
            po.date AS "date",
            po.id AS "purchaseOrder",
            ROUND(SUM(purchase_order_line.qty * product_cost.cost),2) AS total
        FROM 
            purchase_order po
            INNER JOIN purchase_order_line ON po.id = purchase_order_line.purchase_order_id
            INNER JOIN item_cost ON item_cost.id = purchase_order_line.product_cost_id
        GROUP BY 
            po.id
        ORDER BY 
            po.date
    """, nativeQuery = true)
    public List<PurchaseOrderDto> findAllPurchaseOrder();

    /**
     * Returns a list of the purchase order persisted in the dba
     * @return
     */
    @Query(value = """
    SELECT
        po.id AS "id",
        po.status AS "status",
        po.date AS "date",
        po.id AS "purchaseOrder",
        ROUND(SUM(purchase_order_line.qty * product_cost.cost),2) AS total
    FROM 
        purchase_order po
        INNER JOIN purchase_order_line ON po.id = purchase_order_line.purchase_order_id
        INNER JOIN product_cost ON item_cost.id = purchase_order_line.product_cost_id
    WHERE 
        po.status = 0
    GROUP BY 
        po.id
    ORDER BY 
        po.date
    """, nativeQuery = true)
    public List<PurchaseOrderDto> findAllPendingPurchaseOrder();
    
}
