/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.hospital;
import com.mycompany.hospital.binary.Address;
import com.mycompany.hospital.binary.Patiant;
import com.mycompany.hospital.util.DBConnection;
import java.sql.JDBCType;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Andrew Gobert
 */
public class Hospital {

    public static void main(String[] args) throws SQLException {
        Address address = new Address();
        DBConnection connect = new DBConnection();
        address.GetAddress(connect.Connect(), 0);
        
        int number = address.GetNumber();
        String street = address.GetStreet();
        String city = address.GetCity();
        String state = address.GetState();
        int zip = address.GetZip();
        System.out.print(number);
        System.out.print(" ");
        System.out.print(street);
        System.out.print(" ");
        System.out.print(city);
        System.out.print(" ");
        System.out.print(state);
        System.out.print(" ");
        System.out.print(zip);
    }
}
