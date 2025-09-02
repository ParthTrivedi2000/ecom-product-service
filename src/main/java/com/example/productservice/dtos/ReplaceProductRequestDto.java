package com.example.productservice.dtos;

import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class ReplaceProductRequestDto {
    @NonNull
    private String productName;
    @NonNull
    private String productDescription;
    @NonNull
    private String productCategory;
    @NonNull
    private Double productPrice;
    @NonNull
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
