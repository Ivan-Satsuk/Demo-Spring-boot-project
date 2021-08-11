package com.geekbrains.demoboot.specifications;

import com.geekbrains.demoboot.entities.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Locale;


public class ProductSpecifications {
    public  static Specification<Product> titleContains(String word){
        return (Specification<Product>) ( root, criteriaQuery, criteriaBuilder) -> {
              return   criteriaBuilder.like(criteriaBuilder.lower(root.get("title")),"%"+word.toLowerCase(Locale.ROOT)+"%");};
    }

    public static Specification<Product> priceGreaterThanOrEqualTo(int max){
        return (Specification<Product>)(root,criteriaQuery,criteriaBuilder) -> {
               return criteriaBuilder.greaterThanOrEqualTo(root.get("price"),max);};
    }

    public static  Specification <Product> priceLessThanOrEqualTo(int min){
        return (Specification<Product>)(root,criteriaQuery, criteriaBuilder) -> {
               return criteriaBuilder.lessThanOrEqualTo(root.get("price"),min);};
    }
    }



