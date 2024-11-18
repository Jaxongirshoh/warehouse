package dev.wisespirit.warehouse.controller;

import dev.wisespirit.warehouse.dto.ProductDto;
import dev.wisespirit.warehouse.entity.Exceptions.ProductNotFoundException;
import dev.wisespirit.warehouse.entity.Product;
import dev.wisespirit.warehouse.service.ProductService;
import dev.wisespirit.warehouse.utils.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getAll")
    public ApiResponse<List<Product>> getProducts() {
        List<Product> products = productService.findAll();
        return ApiResponse.success(products); // Return successful response with products
    }

    @PostMapping("/add")
    public ApiResponse<List<Product>> addProduct(@ModelAttribute ProductDto productDto) {
        productService.save(productDto.toProduct());
        return ApiResponse.success();
    }

    @GetMapping("/get/{id}")
    public ApiResponse<Product> getProduct(@PathVariable Long id) {
        Product product = productService.findById(id).orElse(null);
        if (product == null) {
            return ApiResponse.error("Product not found", product);
        }
        return ApiResponse.success(product);
    }

    @PostMapping("/update/{id}")
    public ApiResponse<Product> updateProduct(@PathVariable Long id) throws ProductNotFoundException {
        Product product = productService.findById(id).orElse(null);
        if (product == null) {
            return ApiResponse.error("Product not found", product);
        }
        Product updatedProduct = productService.updateDescriptionAndSKUById(id, product);
        return ApiResponse.success(updatedProduct);
    }

    @GetMapping("/delete/{id}")
    public ApiResponse<Product> deleteProduct(@PathVariable Long id) {
        Product product = productService.findById(id).orElse(null);
        if (product == null) {
            return ApiResponse.error("Product not found", product);
        }
        productService.delete(product);
        return ApiResponse.success();
    }
}
