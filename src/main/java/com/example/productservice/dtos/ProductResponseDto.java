package com.example.productservice.dtos;

import com.example.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto {
    private Long Id;
    private String Name;
    private String Description;
    private Double price;
    private String category;
    private String image;

    public static ProductResponseDto fromEntity(Product product) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.Id = product.getId();
        productResponseDto.category = product.getProductCategory();
        productResponseDto.Description = product.getProductDescription();
        productResponseDto.price = product.getProductPrice();
        productResponseDto.image = product.getProductImage();
        productResponseDto.Name = product.getProductName();
        return productResponseDto;
    }
}
