package com.bookstore.commons.beans;

import lombok.Data;

import java.util.Objects;

/*
 * Created by IntelliJ IDEA.
 * @Author: 霍梦威
 * @Date: 2020/4/20 17:31
 */
@Data
public class Product {
    private String id;
    private String name;
    private double price;
    private String category;
    private int pnum;
    private String   imgurl;
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
