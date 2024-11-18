package dev.wisespirit.warehouse;

import dev.wisespirit.warehouse.controller.ProductController;
import dev.wisespirit.warehouse.entity.Contractor;
import dev.wisespirit.warehouse.entity.Product;
import dev.wisespirit.warehouse.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void getProducts_shouldReturnJsonResponse() throws Exception {
        // Arrange
        Product product1 = Product.builder()
                .id(1L)
                .description("Product 1")
                .sku(123)
                .contractor(new Contractor())
                .build();
        Product product2 = Product.builder()
                .id(2L)
                .description("Product 2")
                .sku(124)
                .contractor(new Contractor())
                .build();
        List<Product> products = Arrays.asList(product1, product2);

        when(productService.findAll()).thenReturn(products);

        // Act and Assert
        mockMvc.perform(get("/products")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].description").value("Product 1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].description").value("Product 2"));
    }
}
