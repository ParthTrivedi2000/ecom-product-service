package com.example.productservice.dtos;

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
        product.setProductCategory(productCategory);
        product.setProductPrice(productPrice);
        product.setProductImage(productImage);
        return product;
    }
}
