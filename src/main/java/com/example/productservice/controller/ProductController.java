package com.example.productservice.controller;

import com.example.productservice.dtos.*;
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
    public CreateProductResponseDto createProduct(@RequestBody CreateProductRequestDto createProductRequestdto) {

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
        return CreateProductResponseDto.fromProduct(product);
    }

    @PutMapping("/{productId}")
    public ProductResponseDto replaceProduct(@PathVariable("productId") Long id, @RequestBody ReplaceProductRequestDto request){
        Product product = productService.replaceProduct(id, request.toProduct());
        return ProductResponseDto.fromEntity(product);
    }

    @PatchMapping("/{productId}")
    public ProductResponseDto updateProduct(@PathVariable("productId") Long id, @RequestBody UpdateProductRequestDto request){
        Product response = productService.updateProduct(id, request.toProduct());
        return ProductResponseDto.fromEntity(response);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
    }

}
