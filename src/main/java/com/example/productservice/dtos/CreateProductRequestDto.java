package com.example.productservice.dtos;

import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductRequestDto {

    private String productName;
    private String productDescription;
    private String productCategory;
    private Double productPrice;
    private String productImage;

    public Product toProduct(){
        Product product = new Product();
        product.setProductName(productName);
        product.setProductDescription(productDescription);
        Category category = new Category();
        category.setCategoryName(productCategory);
        product.setProductCategory(category);
        product.setProductPrice(productPrice);
        product.setProductImage(productImage);
        return product;
    }
}