package com.example.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreCreateProductRequestDto {

    private String title;
    private String description;
    private Double price;
    private String image;
    private String category;
}
