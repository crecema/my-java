package com.crecema.my.java.base.mysql.domain;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private Integer id;
    private String name;
    private String email;
    private String phone;
    private String idNumber;
    private Integer status;
    private Sex sex;
    private Integer age;
    private Date createTime;
    private Date updateTime;

}
