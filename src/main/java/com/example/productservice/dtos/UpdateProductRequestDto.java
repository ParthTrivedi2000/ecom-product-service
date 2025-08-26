package com.example.productservice.dtos;

import com.example.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductRequestDto {
    private String productName;
    private String productDescription;
    private String productCategory;
    private Double productPrice;
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
