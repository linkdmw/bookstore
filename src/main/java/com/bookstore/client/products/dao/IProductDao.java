package com.bookstore.client.products.dao;
/*
 * Created by IntelliJ IDEA.
 * @Author: 霍梦威
 * @Date: 2020/4/20 17:42
 */

import com.bookstore.commons.beans.Notice;
import com.bookstore.commons.beans.Product;
import com.bookstore.utils.PageModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface IProductDao {
    List<Product> selectProductByCategory(@Param("category") String category, @Param("pageModel") PageModel pageModel);

    int selectProductCountByCategory(@Param("category") String category);

    List<Product> selectProductByName(@Param("productName") String productName, @Param("pageModel") PageModel pageModel);

    int selectProductCountByName(@Param("productName") String productName);

    Product selectProductById(String id);

    //测试用
    List<Product> selectAllProduct();

    Notice selectNoticeRecent();

    List<Product> selectWeekHotProduct();
}
