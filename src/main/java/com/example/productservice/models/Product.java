package com.example.productservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {

    private Long id;
    private String productName;
    private String productDescription;
    private String productCategory;
    private Double productPrice;
    private String productImage;
}
