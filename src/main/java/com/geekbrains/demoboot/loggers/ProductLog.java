package com.geekbrains.demoboot.loggers;

import com.geekbrains.demoboot.entities.Product;
import com.geekbrains.demoboot.services.ProductsService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Aspect
@Component
public class ProductLog {
    @Autowired
    ProductsService productsService;

    private static Map<Product, Integer> productHistoryChek = new HashMap<>();

    @Pointcut(" execution (* com.geekbrains.demoboot.controllers.ProductsController.showOneProduct(..)) &&" +
            "args(..,id)")
    private void getName(Long id) {
    }

    public static Map<Product, Integer> getProductHistoryChek() {
        return productHistoryChek;
    }

    @After("getName(id)")
    public void logProductChek(Long id) {
        Product product = productsService.getById(id);
        product.setViews(product.getViews()+1);
        productsService.add(product);

    }
}
