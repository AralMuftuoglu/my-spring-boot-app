package com.codewitharal.store.repositories;

import com.codewitharal.store.dtos.ProductSummary;
import com.codewitharal.store.dtos.ProductSummaryDTO;
import com.codewitharal.store.entities.Category;
import com.codewitharal.store.entities.Product;
import jakarta.persistence.PrimaryKeyJoinColumn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> , ProductCriteriaRepository, JpaSpecificationExecutor<Product> {

    //Name
    List<Product> findByName(String name);
    List<Product> findByNameLike(String name);
    List<Product> findByNameContaining(String name);


    //Numbers
    List<Product> findByPrice (BigDecimal price);
    List<Product> findByPriceGreaterThanEqual(BigDecimal price);
    List<Product> findByPriceLessThanEqual(BigDecimal price);
    List<Product> findByPriceBetween(BigDecimal price1, BigDecimal price2);

    //Null
    List<Product> findByDescriptionNull();
    List<Product> findByDescriptionNotNull();

    //Multiple conditions
    List<Product> findByDescriptionNullAndNameNull();

    //Sort
    List<Product> findByNameOrderByPriceAsc(String name);

    //Limit
    List<Product> findTop5ByNameOrderByPrice(String name);
    List<Product> findFirst5ByNameOrderByPrice(String name);

    //Find products whose prices are in a given range and sort by name
    //SQL
    @Procedure("findProductsByPrice")
    List<Product> findProducts( BigDecimal price1,  BigDecimal price2);

    //JPQL
    @Query("select p from Product p where p.price between :price1 and :price2 order by p.name")
    List<Product> findByPriceBetweenOrderByName(@Param("price1") BigDecimal price1, @Param("price2") BigDecimal price2);

    @Query("select count(*) from Product p where p.price between :min and :max ")
    long countProducts(@Param("min") BigDecimal min, @Param("max") BigDecimal max);

    @Modifying
    @Query("update Product p set p.price = :newPrice where p.category.id = :categoryId")
    void updatePriceByCategory(BigDecimal newPrice, Byte categoryId);

    @Query("select p.id , p.name from Product p where p.category = :category")
    List<ProductSummary> findByCategory(@Param("category") Category category);
}