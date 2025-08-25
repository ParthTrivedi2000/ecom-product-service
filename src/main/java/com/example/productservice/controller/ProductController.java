package com.example.productservice.controller;

import com.example.productservice.dtos.CreateProductRequestdto;
import com.example.productservice.dtos.CreateProductResponsedto;
import com.example.productservice.models.Product;
import com.example.productservice.services.IProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final IProductService productService;

    // Constructor Injection
    public ProductController(@Qualifier("fakeStoreProductServiceImpl") IProductService productService) {
        this.productService = productService;
    }


    @GetMapping("")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public Product getSingleProduct(@PathVariable("productId") Long id) {
        return productService.getSingleProduct(id);
    }


    @PostMapping("")
    public CreateProductResponsedto createProduct(@RequestBody CreateProductRequestdto createProductRequestdto) {

        // Just for my understanding purpose:-
        System.out.println(createProductRequestdto.getProductName());
        System.out.println(createProductRequestdto.getProductDescription());
        System.out.println(createProductRequestdto.getProductPrice());
        System.out.println(createProductRequestdto.getProductCategory());
        System.out.println(createProductRequestdto.getProductImage());

        // 1) Getting req from client in the form of some DTO.
        // 2) Creating desired model from that DTO to pas into service layer.
        // 3) Again whatever service is returning, that we are converting into response DTO to return back to the client.
        Product product = productService.createProduct(createProductRequestdto.toProduct());
        return CreateProductResponsedto.fromProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct() {}

}
