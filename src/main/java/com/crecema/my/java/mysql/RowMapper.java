package com.crecema.my.java.mysql;

import java.sql.ResultSet;

public interface RowMapper <T> {

    T mapRow(ResultSet rs) throws Exception;

}
