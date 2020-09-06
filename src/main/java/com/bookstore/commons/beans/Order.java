package com.bookstore.commons.beans;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Order {

  private String id;
  private double money;
  private String receiverAddress;
  private String receiverName;
  private String receiverPhone;
  private int paystate;
  private Timestamp ordertime;
  //关联属性
  private User user;





}
