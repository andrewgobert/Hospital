/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospital.dao.imp;

import com.mycompany.hospital.util.ConnectionPool;
import java.sql.Connection;

/**
 *
 * @author Andrew Gobert
 */
public abstract class AbstractDAO {
    public Connection getConnection() {
        return ConnectionPool.getInstance().getConnection();
    }

    public void releaseConnection(Connection connection) {
        ConnectionPool.getInstance().releaseConnection(connection);
    }
}
