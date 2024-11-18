package dev.wisespirit.warehouse.controller;

import dev.wisespirit.warehouse.entity.Product;
import dev.wisespirit.warehouse.repository.ProductRepository;
import dev.wisespirit.warehouse.service.ProductService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class ProductController {
    private final ProductService productService;
    private static final String PRODUCT_PATH = "products";
    private static final String PRODUCT_PATH_PATH = PRODUCT_PATH + "/add";
    private static final String PRODUCT_PATH_ID = PRODUCT_PATH + "/{itemId}";
    private static final String UPDATE_PRODUCT_PATH_ID = PRODUCT_PATH + "/update";

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
   @GetMapping(PRODUCT_PATH)
    public List<Product> getProducts() {
       List<Product> list = productService.findAll().stream().filter(product -> product.getId() != null).toList();
       return list;
   }


}
