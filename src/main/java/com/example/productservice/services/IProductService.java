package com.example.productservice.services;

import com.example.productservice.models.Product;

public interface IProductService {

    Product createProduct(Product product);
    Product getSingleProduct(Long productId);
}
