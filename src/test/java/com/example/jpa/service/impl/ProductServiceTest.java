package com.example.jpa.service.impl;

import com.example.jpa.data.dto.ProductDto;
import com.example.jpa.data.dto.ProductResponseDto;
import com.example.jpa.data.entity.Product;
import com.example.jpa.data.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

class ProductServiceTest {

    private ProductRepository productRepository = Mockito.mock(ProductRepository.class);
    private ProductServiceImpl productService;

    @BeforeEach
    public void setUpTest() {
        productService = new ProductServiceImpl(productRepository);
    }
    @Test
    void saveProduct() {
        Mockito.when(productRepository.save(any(Product.class)))
                .then(returnsFirstArg());

        ProductResponseDto productResponseDto = productService.saveProduct(new ProductDto("pen", 1000, 1234));

        assertEquals(productResponseDto.getName(), "pen");
        assertEquals(productResponseDto.getPrice(), 1000);
        assertEquals(productResponseDto.getStock(), 1234);

        verify(productRepository).save(any());
    }
}