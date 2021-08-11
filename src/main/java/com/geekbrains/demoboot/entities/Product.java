package com.geekbrains.demoboot.entities;



import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name ="price")
    private int price;

    @Column(name ="views")
    private Integer views;

    public int getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Product() {
    }

    public Product(Long id, String title, int price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Product product = (Product) o;
        return price == product.getPrice() && id.equals(product.getId()) && title.equals(product.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, price);
    }

    @Override
    public String toString() {
        return String.format("{%d, %s, %d}", id, title, price);
    }


}
