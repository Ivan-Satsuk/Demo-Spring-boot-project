package com.geekbrains.demoboot.repositories;

import com.geekbrains.demoboot.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository1 extends JpaRepository<Product,Long>, JpaSpecificationExecutor<Product> {

    List <Product> findTop3ByOrderByViewsDesc();
}
