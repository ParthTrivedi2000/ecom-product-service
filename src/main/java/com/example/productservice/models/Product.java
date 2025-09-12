package com.example.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel {
    private String productName;
    private String productDescription;
    private Double productPrice;
    private String productImage;
    @ManyToOne
    private Category productCategory;
}
