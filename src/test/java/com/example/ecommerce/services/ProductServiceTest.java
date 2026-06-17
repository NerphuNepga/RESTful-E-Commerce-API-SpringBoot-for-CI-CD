package com.example.ecommerce.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.ecommerce.entities.Product;
import com.example.ecommerce.repositories.ProductRepository;
import com.example.ecommerce.services.exceptions.ResourceNotFoundException;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    private Product testProduct;

    @BeforeEach
    void setUp() {
        testProduct = new Product(1L, "Meja", "meja antik", 200000.00, "image/url");
    }

    @Test
    void testFindAll_ReturnsProductList() {
        when(productRepository.findAll()).thenReturn(Arrays.asList(testProduct));
        
        List<Product> result = productService.findAll();
        
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Meja", result.get(0).getName());
        verify(ProductRepository, times(1)).findAll();
    }
}
