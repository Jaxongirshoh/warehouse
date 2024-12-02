package dev.wisespirit.warehouse.repository;

import dev.wisespirit.warehouse.entity.Warehouse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseRepository extends CrudRepository<Warehouse,Long> {
    List<Warehouse> findAllByOrganizationId(Long organizationId);
}
