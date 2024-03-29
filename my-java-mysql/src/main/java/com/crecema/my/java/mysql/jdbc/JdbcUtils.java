package com.crecema.my.java.mysql.jdbc;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Slf4j
public abstract class JdbcUtils {

    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;

    static {
        try {
            Properties jdbcProperties = new Properties();
            jdbcProperties.load(JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties"));
            URL = jdbcProperties.getProperty("jdbc.url");
            USERNAME = jdbcProperties.getProperty("jdbc.username");
            PASSWORD = jdbcProperties.getProperty("jdbc.password");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    /**
     * DDL
     */
    public static boolean execute(String sql, Object... params) {
        try (var connection = getConnection(); var statement = connection.prepareStatement(sql)) {
            for (var i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            return statement.execute();
        } catch (SQLException e) {
            log.error("execute error", e);
            return false;
        }
    }

    /**
     * DML (insert, update, delete)
     */
    public static int executeUpdate(String sql, Object... params) {
        try (var connection = getConnection(); var statement = connection.prepareStatement(sql)) {
            for (var i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            return statement.executeUpdate();
        } catch (SQLException e) {
            log.error("update error", e);
            return 0;
        }
    }

    /**
     * DQL (select)
     */
    public static <T> List<T> executeQuery(String sql, Mapper<T> mapper, Object... params) {
        try (var connection = getConnection(); var statement = connection.prepareStatement(sql)) {
            for (var i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            var resultSet = statement.executeQuery();
            List<T> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(mapper.map(resultSet));
            }
            return list;
        } catch (SQLException e) {
            log.error("query error", e);
            return new ArrayList<>();
        }
    }

    @FunctionalInterface
    public interface Mapper<T> {
        T map(ResultSet resultSet) throws SQLException;
    }

}
