package com.crecema.my.java.mysql;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Slf4j
public abstract class JdbcUtils {

    private static final String DRIVER;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;

    static {
        try {
            Properties jdbcProperties = new Properties();
            jdbcProperties.load(JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties"));
            DRIVER = jdbcProperties.getProperty("jdbc.driver");
            URL = jdbcProperties.getProperty("jdbc.url");
            USERNAME = jdbcProperties.getProperty("jdbc.username");
            PASSWORD = jdbcProperties.getProperty("jdbc.password");
            Class.forName(DRIVER);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static boolean execute(String sql, Object... params) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            return statement.execute();
        } catch (SQLException e) {
            log.error("execute error", e);
            return false;
        } finally {
            close(statement, connection);
        }
    }

    public static int executeUpdate(String sql, Object... params) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            return statement.executeUpdate();
        } catch (SQLException e) {
            log.error("update error", e);
            return 0;
        } finally {
            close(statement, connection);
        }
    }

    public static ResultSet executeQuery(String sql, Object... params) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            return statement.executeQuery();
        } catch (SQLException e) {
            log.error("query error", e);
            return null;
        } finally {
            close(statement, connection);
        }
    }

    public static <T> List<T> executeQuery(String sql, RowMapper<T> rowMapper, Object... params) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<T> results = new ArrayList<>();
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                results.add(rowMapper.mapRow(resultSet));
            }
            return results;
        } catch (Exception e) {
            log.error("query error", e);
            return results;
        } finally {
            close(resultSet, statement, connection);
        }
    }

    public static void close(AutoCloseable... closeables) {
        for (AutoCloseable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (Exception e) {
                    log.error("close error", e);
                }
            }
        }
    }

}
