package dev.wisespirit.warehouse.repository;

import dev.wisespirit.warehouse.entity.PurchaseOrder;
import dev.wisespirit.warehouse.entity.PurchaseOrderLine;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PurchaseOrderLineRepository extends CrudRepository<PurchaseOrderLine, Long> {
    /**
     * Returns a list of puchase order lines by puchase order
     * @param purchaseOrder
     * @return
     */
    public List<PurchaseOrderLine> findByPurchaseOrder(PurchaseOrder purchaseOrder);
    /**
     * Deletes all the purchase order lines by a purhcase order
     * @param purchaseOrder
     */
    public void deleteAllByPurchaseOrder(PurchaseOrder purchaseOrder);
}
