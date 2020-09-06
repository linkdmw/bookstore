package com.bookstore.commons.beans;
/*
 * Created by IntelliJ IDEA.
 * @Author: 霍梦威
 * @Date: 2020/4/8 16:25
 */

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private String gender;
    private String email;
    private String telephone;
    private String introduce;
    private String activeCode;
    private Integer state;
    private String role;
    private Date registTime;
}
