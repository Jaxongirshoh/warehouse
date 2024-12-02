package dev.wisespirit.warehouse.controller;

import dev.wisespirit.warehouse.entity.Warehouse;
import dev.wisespirit.warehouse.service.WarehouseService;
import dev.wisespirit.warehouse.utils.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/warehouses")
@RequiredArgsConstructor
public class WarehouseController {

    private final WarehouseService warehouseService;


    @PostMapping
    public ResponseEntity<?> createWarehouse(@Valid @RequestBody Warehouse warehouse, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Create a map of field errors
            Map<String, String> fieldErrors = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(
                            FieldError::getField,
                            FieldError::getDefaultMessage,
                            (existingValue, newValue) -> existingValue
                    ));

            return ResponseEntity
                    .badRequest()
                    .body(ApiResponse.error("Validation failed", fieldErrors));
        }
        try {
            Warehouse savedWarehouse = warehouseService.save(warehouse);
            return new ResponseEntity<>(savedWarehouse, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }


    @GetMapping
    public ResponseEntity<List<Warehouse>> getAllWarehouses() {
        List<Warehouse> warehouses = warehouseService.findAll();
        return ResponseEntity.ok(warehouses);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> getWarehouseById(@PathVariable Long id) {
        Optional<Warehouse> warehouse = warehouseService.findById(id);
        return warehouse.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<Warehouse> updateWarehouse(
            @PathVariable Long id,
            @Valid @RequestBody Warehouse warehouseDetails) {
        if (!warehouseService.existById(id)){
            return ResponseEntity.notFound().build();
        }
        Optional<Warehouse> updatedWarehouse = warehouseService.update(warehouseDetails,id);
        if (updatedWarehouse.isPresent()){
            return ResponseEntity.ok(updatedWarehouse.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteWarehouse(@PathVariable Long id) {
        if (!warehouseService.existById(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        warehouseService.delete(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/organization/{organizationId}")
    public ResponseEntity<List<Warehouse>> getWarehousesByOrganization(@PathVariable Long organizationId) {
        List<Warehouse> warehouses = warehouseService.findByOrganizationId(organizationId);
        return ResponseEntity.ok(warehouses);
    }
}