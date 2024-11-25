package dev.wisespirit.warehouse.service;

import dev.wisespirit.warehouse.entity.Contractor;
import dev.wisespirit.warehouse.Exceptions.ProductNotFoundException;
import dev.wisespirit.warehouse.entity.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface ProductService {
    public Optional<Product> findById(Long id);

    public Product updateDescriptionAndSKUById(Long id, Product product) throws ProductNotFoundException;

    public Product save(Product product);

    public void delete(Product product);
    public List<Product> findByContractor(Contractor contractor);
    public List<Product> findAll();
}
