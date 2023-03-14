/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospital.util;

/**
 *
 * @author Andrew Gobert
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ConnectionPool {
    private static ConnectionPool instance;
    private List<Connection> connections;
    private int poolSize;
    private String url = DBConfigUtil.getProperty("url");
    private String username = DBConfigUtil.getProperty("username");
    private String password = DBConfigUtil.getProperty("password");

    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);


private ConnectionPool() {
        // initialize variables such as poolSize, url, username, and password based on your database configuration
        poolSize = 10;
        DBConfigUtil.getProperty("url");
        DBConfigUtil.getProperty("username");
        DBConfigUtil.getProperty("password");
        connections = new ArrayList<>(poolSize);
        for (int i = 0; i < poolSize; i++) {
        try {
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                Connection connection = DriverManager.getConnection(url, username, password);
                connections.add(connection);
        }
        catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
        }
        }
}

public static ConnectionPool getInstance() {
        if (instance == null) {
        instance = new ConnectionPool();
        }
        return instance;
        }

public synchronized Connection getConnection() {
        if (connections.isEmpty()) {
        try {
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
        } catch (SQLException e) {
                logger.error("StackTrace", e);
        }
        }
        return connections.remove(connections.size() - 1);
        }

public synchronized void releaseConnection(Connection connection) {
        connections.add(connection);
        }
}
