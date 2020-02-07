package com.solvd.uber.connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.Logger;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private final Logger LOGGER = Logger.getLogger(ConnectionPool.class);
    private static ConnectionPool connectionPool;
    private ComboPooledDataSource cpds;

    private ConnectionPool() {
        try {
            cpds = new ComboPooledDataSource();
            cpds.setDriverClass("com.mysql.cj.jdbc.Driver");
            cpds.setJdbcUrl("jdbc:mysql://localhost:3306/uber");
            cpds.setUser("root");
            cpds.setPassword("asd123");
        } catch (PropertyVetoException e) {
            LOGGER.error(e);
        }
    }

    public static ConnectionPool getInstance() {
        if (connectionPool == null) {
            connectionPool = new ConnectionPool();
        }
        return connectionPool;
    }

    public Connection getConnection() {
        Connection conn = null;
        try {
            conn = cpds.getConnection();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return conn;
    }
}
