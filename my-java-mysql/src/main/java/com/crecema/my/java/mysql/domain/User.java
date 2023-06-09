package com.crecema.my.java.mysql.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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
    private ExtraInfo extraInfo;
    private Date createTime;
    private Date updateTime;

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ExtraInfo {
        private Boolean isStudent;
        private String school;
    }

}
