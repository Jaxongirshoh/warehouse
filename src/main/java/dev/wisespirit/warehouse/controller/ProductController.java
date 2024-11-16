package dev.wisespirit.warehouse.controller;

import dev.wisespirit.warehouse.entity.Product;
import dev.wisespirit.warehouse.repository.ProductRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<Product> getAllProducts(JdbcTemplate jdbcTemplate) {
        return jdbcTemplate.queryForList("select * from products", Product.class);
    }
}
