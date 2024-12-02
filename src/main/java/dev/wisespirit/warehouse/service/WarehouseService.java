package dev.wisespirit.warehouse.service;

import aj.org.objectweb.asm.commons.Remapper;
import dev.wisespirit.warehouse.entity.Warehouse;
import dev.wisespirit.warehouse.repository.WarehouseRepository;
import jakarta.validation.Valid;
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


    public Warehouse save( Warehouse warehouse) {
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

    public Optional<Warehouse> update( Warehouse warehouseDetails,Long id) {
        Warehouse warehouse = warehouseRepository.findById(warehouseDetails.getId()).orElse(null);
        if (warehouse == null) {
            return Optional.empty();
        }
        warehouse.setId(id);
        if (warehouseDetails.getName() != null) {
            warehouse.setName(warehouseDetails.getName());
        }
        if (warehouseDetails.getAddress() != null) {
            warehouse.setAddress(warehouseDetails.getAddress());
        }
        if (warehouseDetails.getPhone() != null) {
            warehouse.setPhone(warehouseDetails.getPhone());
        }
        Warehouse saved = warehouseRepository.save(warehouse);
        return Optional.of(saved);
    }
}
