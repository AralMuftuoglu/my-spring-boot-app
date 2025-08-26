package com.codewitharal.store.repositories.specifications;

import com.codewitharal.store.entities.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpecification {
    public static Specification<Product> hasName(String name) {
        return (root, query, cb) -> cb.like(root.get("name"), "%" +name+"%");
    }

    public static Specification<Product> hasPriceGreaterThanOrEqualTo(BigDecimal price) {
        return ( (root, query, criteriaBuilder) ->  criteriaBuilder.greaterThanOrEqualTo(root.get("price"), price));
    }

    public static Specification<Product> hasPriceLessThanOrEqualTo(BigDecimal price) {
        return ( (root, query, criteriaBuilder) ->   criteriaBuilder.lessThanOrEqualTo(root.get("price"), price));
    }
}
