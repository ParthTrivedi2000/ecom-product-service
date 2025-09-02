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
        Category categoryForProduct = getCategoryForProduct(product);

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
        // Logic :-
        /*
        We have to check weather the id exists or not. If not then we can throw the exception. If yes then save
        the product. But yes again here you need to check for the category separately.
         */
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(optionalProduct.isEmpty()){
            throw new RuntimeException("Product not found");
        }

        Product returnedProduct = optionalProduct.get();

        // Checking if category is available or not
        Category categoryForProduct = getCategoryForProduct(product);
        returnedProduct.setProductCategory(categoryForProduct);

        returnedProduct.setProductName(product.getProductName());
        returnedProduct.setProductDescription(product.getProductDescription());
        returnedProduct.setProductPrice(product.getProductPrice());
        returnedProduct.setProductImage(product.getProductImage());

        return productRepository.save(returnedProduct);
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        // logic:-
        /*
        So if productId already exists then we need to update the product. but here please make sure to check which all
        attributes are provided in the argument to update the product. Be extra careful to deal with category check, we
        need to do the same as we did in the above create method for category.
         */
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(optionalProduct.isEmpty()){
            throw new RuntimeException("Product not found");
        }

        Product updatedProduct = optionalProduct.get();

        // Check for category
        if(product.getProductCategory() != null) {
            Category categoryForProduct = getCategoryForProduct(product);
            // Setting Updated Category to Product object
            updatedProduct.setProductCategory(categoryForProduct);
        }

        if(product.getProductName() != null) {updatedProduct.setProductName(product.getProductName());}
        if(!product.getProductDescription().isEmpty()) {updatedProduct.setProductDescription(product.getProductDescription());}
        if(product.getProductPrice() != null) {updatedProduct.setProductPrice(product.getProductPrice());}
        if(product.getProductImage() != null) {updatedProduct.setProductImage(product.getProductImage());}

        // Saving Updated Product to DB
        return productRepository.save(updatedProduct);
    }

    private Category getCategoryForProduct(Product product) {
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
        return categoryForProduct;
    }

    @Override
    public void deleteProduct(Long productId) {

    }

}
