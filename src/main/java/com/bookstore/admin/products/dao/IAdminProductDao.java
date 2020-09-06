package com.bookstore.admin.products.dao;

import com.bookstore.commons.beans.Product;
import com.bookstore.commons.beans.ProductList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by IDEA.
 *
 * @Author: 霍梦威
 * @Date: 2020/6/3 17:42
 */
@Mapper
@Repository
public interface IAdminProductDao {
    List<Product> selectAllProduct();

    List<Product> selectProductByManyCondition(Map map);

    void insertProduct(Product product);

    Product selectProductById(String id);

    void updateProduct(Product product);

    void deleteProduct(String id);

    List<ProductList> selectProductSalByTime(@Param("year") String year, @Param("month") String month);
}
