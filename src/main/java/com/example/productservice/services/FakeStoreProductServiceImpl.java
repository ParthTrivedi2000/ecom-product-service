package com.example.productservice.services;

import com.example.productservice.dtos.FakeStoreCreateProductRequestdto;
import com.example.productservice.dtos.FakeStoreCreateProductResponsedto;
import com.example.productservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
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
        FakeStoreCreateProductRequestdto request = new FakeStoreCreateProductRequestdto();
        request.setCategory(product.getProductCategory());
        request.setImage(product.getProductImage());
        request.setPrice(product.getProductPrice());
        request.setDescription(product.getProductDescription());
        request.setTitle(product.getProductName());

        // 2) Calling 3rd party API
        FakeStoreCreateProductResponsedto response = restTemplate.postForObject("https://fakestoreapi.com/products/",
                request, FakeStoreCreateProductResponsedto.class);

        // 3) Converting 3rd party API response to the desired service response
        Product returnedProduct = new Product();
        returnedProduct.setProductCategory(response.getCategory());
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
        Optional<FakeStoreCreateProductResponsedto> optionalProductData = Optional.ofNullable(restTemplate.getForObject("https://fakestoreapi.com/products/" + productId, FakeStoreCreateProductResponsedto.class));
        // if not present, return null
        if(optionalProductData.isEmpty()) {return null;}
        // else return corresponding product
        Product returnedProduct = new Product();
        returnedProduct.setProductCategory(optionalProductData.get().getCategory());
        returnedProduct.setProductImage(optionalProductData.get().getImage());
        returnedProduct.setProductPrice(optionalProductData.get().getPrice());
        returnedProduct.setProductDescription(optionalProductData.get().getDescription());
        returnedProduct.setProductName(optionalProductData.get().getTitle());
        returnedProduct.setId(optionalProductData.get().getId());
        return returnedProduct;
    }
}
