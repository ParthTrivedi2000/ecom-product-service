package com.champ.ecomproductservice.repositories;

import com.champ.ecomproductservice.models.Category;
import com.champ.ecomproductservice.models.Product;
import com.champ.ecomproductservice.projections.ProductWithIdAndPriceProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    List<Product> findAll();

    Product save(Product p);

    @Override
    Optional<Product> findById(Long id);

    List<Product> findByProductCategory(Category productCategory);

    /*
    Will you always have the entire category object when you want to fetch all
    products with an associated category
     */

    List<Product> findAllByProductCategory_CategoryName(String categoryName);//Declared queries
    /*

     */
    List<Product> findAllByProductCategory_Id(Long id);

    /*
    We don't have complete control over the query that JPA will execute for us?

    I am interested only in certain columns. I'll provide the query?
    HQL - Similar to SQL but with a small pinch of OOP.
     */

    /*
    Providing the query to JPA can be done :-
    1. HQL
    2. Native SQL
     */

    @Query("select p.id, p.productPrice from Product p where p.productCategory.categoryName = :categoryName")
    List<ProductWithIdAndPriceProjection> getProductTitlesAndPricesAndAGivenCategoryName(@Param("categoryName") String categoryName);

    // So above query is HQL (HiberNamte Query Language) and below one is Native SQL.
    // Please note here that in the above query, i.e. in HQL, from clause represent the Object of our application
    // while in Native SQL, from clause represents the table inside DB. please remember this thing.
    @Query(value = "select * from products p where p.title = :title", nativeQuery = true)
    List<ProductWithIdAndPriceProjection> getIdAndPricesOfAllProductsWithGivenTitle(@Param("title") String title);
}
