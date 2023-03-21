package com.example.jpa.data.dao.impl;

import com.example.jpa.data.dao.ProductDAO;
import com.example.jpa.data.entity.Product;
import com.example.jpa.data.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductDAOImpl implements ProductDAO {
    private final ProductRepository productRepository;

    @Override
    public Product insertProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product selectProduct(Long number) {
        return productRepository.getReferenceById(number);
    }

    @Override
    public Product updateProductName(Long number, String name) throws Exception {
        Optional<Product> selectedProduct = productRepository.findById(number);

        Product updatedProduct;
        if (selectedProduct.isPresent()) {
            Product product = selectedProduct.get();

            product.setName(name);
            product.setUpdatedAt(LocalDateTime.now());

            updatedProduct = productRepository.save(product);
        } else {
            throw new Exception();
        }

        return updatedProduct;
    }

    @Override
    public void deleteProduct(Long number) throws Exception {
        Optional<Product> selectedProduct = productRepository.findById(number);

        if (selectedProduct.isPresent()) {
            Product product = selectedProduct.get();
            productRepository.delete(product);
        } else {
            throw new Exception();
        }
    }
}
