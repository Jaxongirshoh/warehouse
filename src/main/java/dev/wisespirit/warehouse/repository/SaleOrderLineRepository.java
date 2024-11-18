package dev.wisespirit.warehouse.repository;

import com.example.warehouseManagement.Domains.SalesOrder;
import com.example.warehouseManagement.Domains.SalesOrderLine;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface SaleOrderLineRepository extends CrudRepository<SalesOrderLine, Long>{

    /**
     * Returns a list of sales order lines by sales order
     * @param salesOrder
     * @return
     */
    public List<SalesOrderLine> findBySalesOrder(SalesOrder salesOrder);
    /**
     * Deletes all the sales order lines by a sales order
     * @param salesOrder
     */
    public void deleteAllBySalesOrder(SalesOrder salesOrder);
    
}
