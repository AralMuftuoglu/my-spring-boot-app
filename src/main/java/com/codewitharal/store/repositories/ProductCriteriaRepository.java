package com.codewitharal.store.repositories;

import com.codewitharal.store.entities.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductCriteriaRepository {

    List<Product> findProductsByCriteria (String name, BigDecimal minPrice, BigDecimal maxPrice);
}
