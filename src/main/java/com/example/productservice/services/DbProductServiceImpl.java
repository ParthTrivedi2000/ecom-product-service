package com.example.productservice.services;

import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.CategoryRepository;
import com.example.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("dbProductServiceImpl")
public class DbProductServiceImpl implements IProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    // Constructor Injection
    public DbProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product createProduct(Product product) {
        // logic :-
        /*
        for any new product:- 1st check if category exists or not? --> if yes just create product in db and return resp
        else if not, then 1st create the category in the category table then create the product in the db.
         */
        String category = product.getProductCategory().getCategoryName();
        Optional<Category> categoryOptional = categoryRepository.findByCategoryName(category);
        Category categoryForProduct = null;
        if(categoryOptional.isEmpty()) {
            // Creating Category Object
            Category newCategory = new Category();
            newCategory.setCategoryName(category);
            newCategory.setCategoryDescription(product.getProductCategory().getCategoryDescription());
            categoryForProduct = categoryRepository.save(newCategory);
        } else {
            categoryForProduct = categoryOptional.get();
        }

        // Setting Updated Category to Product object
        product.setProductCategory(categoryForProduct);
        Product returnedProduct = productRepository.save(product);
        return returnedProduct;
    }

    @Override
    public Product getSingleProduct(Long productId) {
         Optional<Product> optionalProduct = productRepository.findById(productId);
        return optionalProduct.orElse(null);
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> returnedListOfProducts = productRepository.findAll();
        return returnedListOfProducts;
    }

    @Override
    public Product replaceProduct(Long productId, Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        return null;
    }

    @Override
    public void deleteProduct(Long productId) {

    }

}
