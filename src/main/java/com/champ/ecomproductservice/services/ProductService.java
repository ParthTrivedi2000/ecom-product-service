package com.champ.ecomproductservice.services;

import com.champ.ecomproductservice.exceptions.ProductNotFoundException;
import com.champ.ecomproductservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    public List<Product> getAllProducts();
    public Product getProductById(Long id) throws ProductNotFoundException;
    public Product createProduct(String productName, String productDescription, String productCategory, Double productPrice, String productImage);
    public Product updateProduct(Product product);
    public void deleteProduct(int id);
}
