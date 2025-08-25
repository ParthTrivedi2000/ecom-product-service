package com.example.productservice.services;

import com.example.productservice.models.Product;
import org.springframework.stereotype.Service;

@Service("dbProductServiceImpl")
public class DbProductServiceImpl implements IProductService {

    @Override
    public Product createProduct(Product product) {
        return null;
    }

}
