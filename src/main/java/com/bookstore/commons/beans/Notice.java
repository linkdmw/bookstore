package com.bookstore.commons.beans;

import lombok.Data;

import java.sql.Date;

@Data
public class Notice {

  private Integer n_id;
  private String title;
  private String details;
  private Date n_time;

}
