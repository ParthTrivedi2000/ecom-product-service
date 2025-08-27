package com.example.productservice.services;

import com.example.productservice.models.Product;

import java.util.List;

public interface IProductService {

    Product createProduct(Product product);
    Product getSingleProduct(Long productId);
    List<Product> getAllProducts();
    Product replaceProduct(Long productId, Product product);
    Product updateProduct(Long productId, Product product);
    void deleteProduct(Long productId);
}
