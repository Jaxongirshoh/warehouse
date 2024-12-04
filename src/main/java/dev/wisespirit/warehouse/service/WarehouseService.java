package dev.wisespirit.warehouse.service;

import dev.wisespirit.warehouse.dto.auth.WarehouseDto;
import dev.wisespirit.warehouse.entity.Warehouse;
import dev.wisespirit.warehouse.repository.WarehouseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;

    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }


    public Warehouse save( WarehouseDto dto) {
        Warehouse warehouse = new Warehouse();
        warehouse.setPhone(dto.phone());
        warehouse.setName(dto.name());
        warehouse.setAddress(dto.address());
        warehouse.setOrganizationId(dto.organizationId());
        return warehouseRepository.save(warehouse);
    }

    public List<Warehouse> findAll() {
        Iterable<Warehouse> iterable = warehouseRepository.findAll();
        List<Warehouse> warehouses = new ArrayList<>();
        iterable.forEach(warehouses::add);
        return warehouses;
    }

    public Optional<Warehouse> findById(Long id) {
        Warehouse warehouse = warehouseRepository.findById(id).orElse(null);
       return Optional.of(warehouse);
    }

    public List<Warehouse> findByOrganizationId(Long organizationId) {
        return warehouseRepository.findAllByOrganizationId(organizationId);
    }

    public void delete(Long id) {
        warehouseRepository.deleteById(id);
    }

    public boolean existById(Long id) {
        return warehouseRepository.existsById(id);
    }

    public Optional<Warehouse> update(WarehouseDto warehouseDetails, Long id) {
        Warehouse warehouse = new Warehouse();
        warehouse.setName(warehouseDetails.name());
        warehouse.setAddress(warehouseDetails.address());
        warehouse.setOrganizationId(warehouseDetails.organizationId());
        warehouse.setPhone(warehouseDetails.phone());
        Warehouse savedWarehouse = warehouseRepository.findById(id).orElse(null);
        if (savedWarehouse == null) {
            return Optional.empty();
        }
        savedWarehouse.setId(id);
        if (warehouseDetails.name() != null) {
            savedWarehouse.setName(warehouseDetails.name());
        }
        if (warehouseDetails.address() != null) {
            savedWarehouse.setAddress(warehouseDetails.address());
        }
        if (warehouseDetails.phone() != null) {
            savedWarehouse.setPhone(warehouseDetails.phone());
        }
        Warehouse saved = warehouseRepository.save(savedWarehouse);
        return Optional.of(saved);
    }
}
