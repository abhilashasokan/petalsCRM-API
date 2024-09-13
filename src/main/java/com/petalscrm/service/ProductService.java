package com.petalscrm.service;


import com.petalscrm.model.Product;
import com.petalscrm.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        return productRepository.findById(id).map(product -> {
            product.setProductName(updatedProduct.getProductName());
            product.setDescription(updatedProduct.getDescription());
            product.setBasePrice(updatedProduct.getBasePrice());
            product.setCostOfGood(updatedProduct.getCostOfGood());
            product.setProfit(updatedProduct.getProfit());
            product.setStatus(updatedProduct.getStatus()); // Ensure this is of type ProductStatus
            return productRepository.save(product);
        }).orElseThrow(() -> new RuntimeException("Product not found with id " + id));
    }
}
