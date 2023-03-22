package com.example.jpa.repository;

import com.example.jpa.data.dto.ProductDto;
import com.example.jpa.data.entity.Product;
import com.example.jpa.data.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


@SpringBootTest
public class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;

    @Test
    public void basicCRUDTest() {
        //create
        Product givenProduct = Product.builder()
                .name("pen")
                .price(5000)
                .stock(2000)
                .build();
        Product savedProduct = productRepository.save(givenProduct);

        assertThat(savedProduct.getNumber())
                .isEqualTo(givenProduct.getNumber());
        assertThat(savedProduct.getName())
                .isEqualTo(givenProduct.getName());
        assertThat(savedProduct.getPrice())
                .isEqualTo(givenProduct.getPrice());
        assertThat(savedProduct.getStock())
                .isEqualTo(givenProduct.getStock());


        //read
        Product selectedProduct = productRepository.findById(savedProduct.getNumber())
                .orElseThrow(RuntimeException::new);

        assertThat(selectedProduct.getNumber())
                .isEqualTo(givenProduct.getNumber());
        assertThat(selectedProduct.getName())
                .isEqualTo(givenProduct.getName());
        assertThat(selectedProduct.getPrice())
                .isEqualTo(givenProduct.getPrice());
        assertThat(selectedProduct.getStock())
                .isEqualTo(givenProduct.getStock());


        //update
        Product foundProduct = productRepository.findById(selectedProduct.getNumber())
                .orElseThrow(RuntimeException::new);
        foundProduct.setName("toy");

        Product updatedProduct = productRepository.save(foundProduct);
        assertEquals(updatedProduct.getName(), "toy");


        //delete
        productRepository.delete(updatedProduct);
        assertFalse(productRepository.findById(selectedProduct.getNumber()).isPresent());
    }
}
