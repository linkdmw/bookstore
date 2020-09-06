package com.bookstore.admin.products.service;

import com.bookstore.commons.beans.Product;
import com.bookstore.commons.beans.ProductList;

import java.util.List;

/**
 * Created by IDEA.
 *
 * @Author: 霍梦威
 * @Date: 2020/6/3 17:40
 */

public interface IAdminProductService {
    List<Product> findAllProduct();

    List<Product> findProductByManyCondition(Product product, Double maxPrice, Double minPrice);

    void addProduct(Product product);

    Product findProductById(String id);

    void modifyProduct(Product product);

    void removeProductById(String id);

    List<ProductList> downloadByTime(String year, String month);
}
