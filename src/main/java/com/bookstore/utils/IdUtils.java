package com.bookstore.utils;
/*
 * Created by IntelliJ IDEA.
 * @Author: 霍梦威
 * @Date: 2020/4/12 22:18
 */

import java.util.UUID;

public class IdUtils {
    public static String getUUID(){
        return UUID.randomUUID().toString();
    }
}
