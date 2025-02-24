package com.champ.ecomproductservice.services;

import com.champ.ecomproductservice.exceptions.ProductNotFoundException;
import com.champ.ecomproductservice.models.Category;
import com.champ.ecomproductservice.models.Product;
import com.champ.ecomproductservice.repositories.CategoryRepository;
import com.champ.ecomproductservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("SelfProductService")
public class SelfProductServiceImpl implements ProductService{


    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    // Constructor Injection
    public SelfProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(id);
                //.orElseThrow(() -> new RuntimeException("Product not found")); // can use this as well instead of Optional<> clause

        //product with the given id doesn't exist
        //either you can throw an exception further or you handle it
        if(product.isEmpty()) throw new ProductNotFoundException("Product with given id is not found");
        return product.get();
    }

    @Override
    public Product createProduct(String productName, String productDescription, String productCategory, Double productPrice, String productImage) {
        // Checking if the received category already exists? --> if yes, create product for that category only
        // else create new category and save it to DB.
        Category categoryFromDB = categoryRepository.findByCategoryName(productCategory);

        Product createdProduct = new Product();
        createdProduct.setProductName(productName);
        createdProduct.setProductDescription(productDescription);
        createdProduct.setProductImage(productImage);
        createdProduct.setProductPrice(productPrice);

        if(categoryFromDB == null) {
            Category cate = new Category();
            cate.setCategoryName(productCategory);
            createdProduct.setProductCategory(cate);
            // categoryRepository.save(categoryFromDB);
        }
        else createdProduct.setProductCategory(categoryFromDB);

        productRepository.save(createdProduct);
        return createdProduct;

    }


    @Override
    public Product updateProduct(Product product) {
        return null;
    }

    @Override
    public void deleteProduct(int id) {
        return;
    }
}
