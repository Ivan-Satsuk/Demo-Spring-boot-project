package com.geekbrains.demoboot.controllers;

import com.geekbrains.demoboot.entities.Product;
import com.geekbrains.demoboot.services.ProductsService;
import com.geekbrains.demoboot.specifications.ProductSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Comparator;

@Controller
@RequestMapping("/products")
public class ProductsController {
    private ProductsService productsService;

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping
    public String showProductsList(Model model, Principal principal) {
        Product product = new Product();
        model.addAttribute("products", productsService.getAllProductsOnPages(0).getContent());

        model.addAttribute("product", product);
        model.addAttribute("page", 0);
        model.addAttribute("hits", productsService.top3TheMostPopularProducts());
        if (principal != null) {
            model.addAttribute("principal", principal.getName());
        } else {
            model.addAttribute("principal", "Unknown_Rabbit");
        }
        return "products";
    }


    @GetMapping("/page/{page}")
    public String showProductsWithFilters(Model model, Principal principal,
                                          @PathVariable(value = "page", required = false) Integer page,
                                          @RequestParam(value = "substring", required = false) String substring,
                                          @RequestParam(value = "min", required = false) Integer min,
                                          @RequestParam(value = "max", required = false) Integer max) {

        Specification<Product> productSpecification = Specification.where(null);
        if (substring != null) {
            productSpecification = productSpecification.and(ProductSpecifications.titleContains(substring));
        }
        if (max != null) {
            productSpecification = productSpecification.and(ProductSpecifications.priceLessThanOrEqualTo(max));
        }
        if (min != null) {
            productSpecification = productSpecification.and(ProductSpecifications.priceGreaterThanOrEqualTo(min));
        }
        if (page == null || page == 0) {
            page = 1;
        }

        Page<Product> productPage = productsService.getAllProductsFiltered(page - 1, productSpecification);
        model.addAttribute("products", productPage.getContent());
        model.addAttribute("substring", substring);
        model.addAttribute("min", min);
        model.addAttribute("max", max);
        model.addAttribute("hits", productsService.top3TheMostPopularProducts());


        if (principal != null) {
            model.addAttribute("principal", principal.getName());
        } else {
            model.addAttribute("principal", "Unknown_Rabbit");
        }
        return "products";

    }

    @GetMapping("/add")
    public String addPageRedirect(Model model) {
        Product product = new Product();
        model.addAttribute(product);
        return "product_add";
    }

    @PostMapping("/product_add/add")
    public String addProduct(@ModelAttribute(value = "product") Product product) {
        if (product != null && product.getPrice() != 0 && product.getTitle() != null) {
            productsService.add(product);
        }
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(Model model, @PathVariable(value = "id") Long id) {
        Product product = productsService.getById(id);
        productsService.delete(product);
        return "redirect:/products";
    }


    @GetMapping("/show/{id}")
    public String showOneProduct(Model model, @PathVariable(value = "id") Long id) {
        Product product = productsService.getById(id);
        model.addAttribute("product", product);
        return "product-page";
    }


    @GetMapping("/edit/{id}")
    public String editProduct(Model model, @PathVariable(value = "id") Long id) {
        Product product = productsService.getById(id);
        model.addAttribute("product", product);
        return "edit_product";
    }

    @PostMapping("/addEdit")
    public String addEditProduct(@ModelAttribute(value = "product") Product product) {
        Product product1 = productsService.getById(product.getId());
        product1.setPrice(product.getPrice());
        product1.setTitle(product.getTitle());
        productsService.add(product1);
        return "redirect:/products";


    }

}
