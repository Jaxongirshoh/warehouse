package dev.wisespirit.warehouse.repository;

import dev.wisespirit.warehouse.entity.Contractor;
import dev.wisespirit.warehouse.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.lang.module.Configuration;
import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findByContractorId(Contractor contractor);
}
