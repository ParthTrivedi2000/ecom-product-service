package com.champ.ecomproductservice.controllers;

import com.champ.ecomproductservice.dtos.CreateProductRequestDto;
import com.champ.ecomproductservice.exceptions.ProductNotFoundException;
import com.champ.ecomproductservice.models.Product;
import com.champ.ecomproductservice.services.ProductService;
import com.champ.ecomproductservice.services.SelfProductServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    // performing Constructor Injection
    private final ProductService productService;

    // Constructor
    public ProductController(@Qualifier("SelfProductService") ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/products/")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable("id") Long productId) throws ProductNotFoundException {
        //either handle the exception or throw it further
        // So we are throwing it further
        return productService.getProductById(productId);
    }

    @PostMapping("products")
    public Product createProduct(@RequestBody CreateProductRequestDto request){

        return productService.createProduct(
                request.getProductName(),
                request.getProductDescription(),
                request.getProductCategory(),
                request.getProductPrice(),
                request.getProductImage()
        );
    }

    @PutMapping("products/{id}")
    public Product updateProduct(Product product){
        return productService.updateProduct(product);
    }

    @DeleteMapping("products/{id}")
    public void deleteProduct(@PathVariable("id") int productId){
        productService.deleteProduct(productId);
    }
}
