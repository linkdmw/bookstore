package com.bookstore.admin.login.dao;

import com.bookstore.commons.beans.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created by IDEA.
 *
 * @Author: 霍梦威
 * @Date: 2020/5/20 21:30
 */
@Mapper
@Repository
public interface IAdminUserDao {
    User selectAdminUserByLogin(User user);
}
