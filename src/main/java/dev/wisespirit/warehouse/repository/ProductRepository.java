package dev.wisespirit.warehouse.repository;

import dev.wisespirit.warehouse.entity.Contractor;
import dev.wisespirit.warehouse.entity.product.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findByContractorId(Contractor contractor);
}
