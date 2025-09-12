package com.example.productservice.services;

import com.example.productservice.dtos.FakeStoreCreateProductRequestDto;
import com.example.productservice.dtos.FakeStoreCreateProductResponseDto;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("fakeStoreProductServiceImpl")
public class FakeStoreProductServiceImpl implements IProductService {

    private RestTemplate restTemplate;

    FakeStoreProductServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product createProduct(Product product) {

        // 1) Creating request to be sent to 3rd party API (here FakeStoreApi)
        FakeStoreCreateProductRequestDto request = new FakeStoreCreateProductRequestDto();
        request.setCategory(product.getProductCategory().getCategoryName());
        request.setImage(product.getProductImage());
        request.setPrice(product.getProductPrice());
        request.setDescription(product.getProductDescription());
        request.setTitle(product.getProductName());

        // 2) Calling 3rd party API
        FakeStoreCreateProductResponseDto response = restTemplate.postForObject("https://fakestoreapi.com/products/",
                request, FakeStoreCreateProductResponseDto.class);

        // 3) Converting 3rd party API response to the desired service response
        Product returnedProduct = new Product();
        Category category = new Category();
        category.setCategoryName(response.getCategory());
        returnedProduct.setProductCategory(category);
        returnedProduct.setProductImage(response.getImage());
        returnedProduct.setProductPrice(response.getPrice());
        returnedProduct.setProductDescription(response.getDescription());
        returnedProduct.setProductName(response.getTitle());
        returnedProduct.setId(response.getId());

        return returnedProduct;
    }

    @Override
    public Product getSingleProduct(Long productId) {
        // Suppose if Id is not present. exa:- 10021
        Optional<FakeStoreCreateProductResponseDto> optionalProductData = Optional.ofNullable(restTemplate.getForObject("https://fakestoreapi.com/products/" + productId, FakeStoreCreateProductResponseDto.class));
        // if not present, return null. Later I will add exception.
        if(optionalProductData.isEmpty()) {return null;}
        // else return corresponding product
        Product returnedProduct = new Product();
        Category category = new Category();
        category.setCategoryName(optionalProductData.get().getCategory());
        returnedProduct.setProductCategory(category);
        returnedProduct.setProductImage(optionalProductData.get().getImage());
        returnedProduct.setProductPrice(optionalProductData.get().getPrice());
        returnedProduct.setProductDescription(optionalProductData.get().getDescription());
        returnedProduct.setProductName(optionalProductData.get().getTitle());
        returnedProduct.setId(optionalProductData.get().getId());
        return returnedProduct;
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreCreateProductResponseDto[] responseList = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreCreateProductResponseDto[].class);
        List<Product> productsList = new ArrayList<>();
        for(FakeStoreCreateProductResponseDto response: responseList) {
            Product product = new Product();
            Category category = new Category();
            category.setCategoryName(response.getCategory());
            product.setProductCategory(category);
            product.setProductImage(response.getImage());
            product.setProductPrice(response.getPrice());
            product.setProductDescription(response.getDescription());
            product.setProductName(response.getTitle());
            product.setId(response.getId());
            productsList.add(product);
        }
        return productsList;
    }

    @Override
    public Product replaceProduct(Long productId, Product product) {

        FakeStoreCreateProductRequestDto request = new FakeStoreCreateProductRequestDto();
        request.setCategory(product.getProductCategory().getCategoryName());
        request.setImage(product.getProductImage());
        request.setPrice(product.getProductPrice());
        request.setDescription(product.getProductDescription());
        request.setTitle(product.getProductName());

        ResponseEntity<FakeStoreCreateProductResponseDto> response = restTemplate.exchange("https://fakestoreapi.com/products/"+productId, HttpMethod.PUT, new HttpEntity<>(request), FakeStoreCreateProductResponseDto.class);

        Product returnedProduct = new Product();
        Category category = new Category();
        category.setCategoryName(response.getBody().getCategory());
        returnedProduct.setProductCategory(category);
        returnedProduct.setProductImage(response.getBody().getImage());
        returnedProduct.setProductPrice(response.getBody().getPrice());
        returnedProduct.setProductDescription(response.getBody().getDescription());
        returnedProduct.setProductName(response.getBody().getTitle());
        returnedProduct.setId(response.getBody().getId());
        return returnedProduct;
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        FakeStoreCreateProductRequestDto request = new FakeStoreCreateProductRequestDto();
        request.setCategory(product.getProductCategory().getCategoryName());
        request.setImage(product.getProductImage());
        request.setPrice(product.getProductPrice());
        request.setDescription(product.getProductDescription());
        request.setTitle(product.getProductName());

        // PATCH method type is not supported by FakeStoreAPI, so keeping method type as PUT only.
        ResponseEntity<FakeStoreCreateProductResponseDto> response = restTemplate.exchange("https://fakestoreapi.com/products/"+productId, HttpMethod.PUT, new HttpEntity<>(request), FakeStoreCreateProductResponseDto.class);

        Product returnedProduct = new Product();
        Category category = new Category();
        category.setCategoryName(response.getBody().getCategory());
        returnedProduct.setProductCategory(category);
        returnedProduct.setProductImage(response.getBody().getImage());
        returnedProduct.setProductPrice(response.getBody().getPrice());
        returnedProduct.setProductDescription(response.getBody().getDescription());
        returnedProduct.setProductName(response.getBody().getTitle());
        returnedProduct.setId(response.getBody().getId());
        return returnedProduct;
    }

    @Override
    public void deleteProduct(Long productId) {
        restTemplate.delete("https://fakestoreapi.com/products/"+productId);
        return;
    }
}
