package dev.wisespirit.warehouse.controller;

import dev.wisespirit.warehouse.dto.ProductDto;
import dev.wisespirit.warehouse.dto.ProductInfoDto;
import dev.wisespirit.warehouse.Exceptions.ProductNotFoundException;
import dev.wisespirit.warehouse.entity.product.Product;
import dev.wisespirit.warehouse.service.ProductService;
import dev.wisespirit.warehouse.utils.ApiResponse;
import dev.wisespirit.warehouse.utils.EntityToDtoUtil;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Retrieves all products and converts them to ProductInfoDto.
     *
     * @return ApiResponse with a list of ProductInfoDto.
     */
    @GetMapping("/getAll")
    public ApiResponse<List<ProductInfoDto>> getProducts() {
        List<Product> products = productService.findAll();
        List<ProductInfoDto> productInfoDtos = EntityToDtoUtil.convertToDtos(products, ProductInfoDto.class);
        return ApiResponse.success(productInfoDtos);
    }

    /**
     * Adds a new product based on the provided ProductDto.
     *
     * @param productDto The ProductDto with product details.
     * @return ApiResponse indicating success or failure.
     */
    @PostMapping("/add")
    public ApiResponse<ProductDto> addProduct(@ModelAttribute ProductDto productDto) {
        productService.save(EntityToDtoUtil.convertToEntity(productDto, Product.class));
        return ApiResponse.success(productDto);
    }

    /**
     * Retrieves a single product by ID and converts it to ProductInfoDto.
     *
     * @param id The ID of the product.
     * @return ApiResponse with the ProductInfoDto.
     */
    @GetMapping("/get/{id}")
    public ApiResponse<ProductInfoDto> getProduct(@PathVariable Long id) throws ProductNotFoundException {
        Product product = productService.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + id));
        ProductInfoDto productInfoDto = EntityToDtoUtil.convertToEntity(product, ProductInfoDto.class);
        return ApiResponse.success(productInfoDto);
    }

    /**
     * Updates a product's description and SKU by ID.
     *
     * @param id The ID of the product.
     * @param productDto The updated product details.
     * @return ApiResponse with the updated product.
     * @throws ProductNotFoundException If the product is not found.
     */
    @PostMapping("/update/{id}")
    public ApiResponse<ProductInfoDto> updateProduct(@PathVariable Long id, @ModelAttribute ProductDto productDto) throws ProductNotFoundException {
        Product existingProduct = productService.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + id));

        existingProduct.setDescription(productDto.getDescription());
        existingProduct.setSku(productDto.getSku());
        Product updatedProduct = productService.updateDescriptionAndSKUById(id, (existingProduct));

        ProductInfoDto productInfoDto = EntityToDtoUtil.convertToEntity(updatedProduct, ProductInfoDto.class);
        return ApiResponse.success(productInfoDto);
    }

    /**
     * Deletes a product by ID.
     *
     * @param id The ID of the product.
     * @return ApiResponse indicating success or failure.
     */
    @DeleteMapping("/delete/{id}")
    public ApiResponse<Void> deleteProduct(@PathVariable Long id) throws ProductNotFoundException {
        Product product = productService.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + id));
        productService.delete(product);
        return ApiResponse.success();
    }
}
