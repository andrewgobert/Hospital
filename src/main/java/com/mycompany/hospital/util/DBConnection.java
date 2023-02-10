/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospital.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Andrew Gobert
 */
public class DBConnection {
    private String user = "root";
    private String password = "nelson268";
    private String url = "jdbc:mysql://localhost:3306/hospital";
    
    public Connection Connect() throws SQLException{
        Connection con = null;
        Properties ConnectionProps = new Properties();
        ConnectionProps.put("user", this.user);
        ConnectionProps.put("password", this.password);
        con = DriverManager.getConnection(url, ConnectionProps);
        return con;
    }
    
}
