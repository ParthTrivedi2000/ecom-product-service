package com.example.productservice.dtos;

import com.example.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductResponseDto {

    private Long Id;
    private String Name;
    private String Description;
    private Double price;
    private String category;
    private String image;

    public static CreateProductResponseDto fromProduct(Product product) {
        CreateProductResponseDto dto = new CreateProductResponseDto();
        dto.Id = product.getId();
        dto.Name = product.getProductName();
        dto.Description = product.getProductDescription();
        dto.price = product.getProductPrice();
        dto.category = product.getProductCategory();
        dto.image = product.getProductImage();
        return dto;
    }
}
