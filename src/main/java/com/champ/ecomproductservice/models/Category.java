package com.champ.ecomproductservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

//@Getter
//@Setter
//@NoArgsConstructor
//@RequiredArgsConstructor
@Entity(name = "Category")
public class Category extends BaseModel{
    private String categoryName;
    @OneToMany(mappedBy = "productCategory", cascade = CascadeType.PERSIST)
    private List<Product> products;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
