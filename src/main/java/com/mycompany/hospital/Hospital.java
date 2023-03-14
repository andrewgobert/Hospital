/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.hospital;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author Andrew Gobert
 */
public class Hospital {

    public static void main(String[] args) throws SQLException, FileNotFoundException, XMLStreamException, ClassNotFoundException, IOException {
        FileInputStream fis=new FileInputStream("C:\\Users\\Andrew Gobert\\Documents\\NetBeansProjects\\Hospital\\src\\main\\java\\com\\mycompany\\Resources\\db.properties");
        Properties p = new Properties();
        p.load (fis);
        String dname = (String) p.get ("Dname"); 
        String url = (String) p.get ("url"); 
        String username= (String) p.get ("username"); 
        String password= (String) p.get ("password");
        Class.forName(dname);
        Connection con = DriverManager.getConnection(url, username, password);
        PreparedStatement preparedStatement = con.prepareStatement("select * from address");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int idcontacts = resultSet.getInt("idaddress");
            String fname = resultSet.getString("number");
            String lname = resultSet.getString("street");
            String address = resultSet.getString("city");
            String primary = resultSet.getString("state");
            int mobile = resultSet.getInt("zip");
            System.out.print(idcontacts);
            System.out.print(fname);
            System.out.print(lname);
            System.out.print(address);
            System.out.print(primary);
            System.out.print(mobile);
        }
    }
}
