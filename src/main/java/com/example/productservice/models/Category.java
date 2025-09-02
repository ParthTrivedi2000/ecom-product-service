package com.example.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel {
    private String categoryName;
    private String categoryDescription;
    @OneToMany(mappedBy = "productCategory")
    private List<Product> allProducts;
    @OneToMany
    private List<Product> featuredProducts;
}
