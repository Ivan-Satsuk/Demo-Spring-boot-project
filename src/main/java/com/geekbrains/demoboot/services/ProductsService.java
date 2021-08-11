package com.geekbrains.demoboot.services;

import com.geekbrains.demoboot.entities.Product;
import com.geekbrains.demoboot.loggers.ProductLog;
import com.geekbrains.demoboot.repositories.ProductRepository1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductsService {
    private ProductRepository1 productRepository1;


    @Autowired
    public void setProductRepository(ProductRepository1 productRepository1) {
        this.productRepository1 = productRepository1;
    }

    public Product getById(Long id) {
        return  productRepository1.getOne(id);
    }

    public List<Product> getAllProducts(Specification<Product> productSpecification) {
        return productRepository1.findAll(productSpecification);
    }
    public Page <Product> getAllProductsOnPages(int page){
        return productRepository1.findAll(PageRequest.of(page,10));
    }

    public Page <Product> getAllProductsFiltered(int page, Specification<Product> productSpecification){
        return productRepository1.findAll(productSpecification, PageRequest.of(page,10,Sort.by("id").ascending()));
    }

    public void add(Product product) {
        productRepository1.save(product);
    }

    public List <Product> top3TheMostPopularProducts(){
        return productRepository1.findTop3ByOrderByViewsDesc();
    }

    public void delete(Product product){
        productRepository1.delete(product);
    }

}
