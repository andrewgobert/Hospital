/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.hospital;
import com.mycompany.hospital.binary.Address;
import com.mycompany.hospital.binary.Patiant;
import com.mycompany.hospital.services.imp.AddressServiceImp;
import com.mycompany.hospital.util.DBConnection;
import java.io.FileNotFoundException;
import java.sql.JDBCType;
import java.sql.*;
import java.util.*;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author Andrew Gobert
 */
public class Hospital {

    public static void main(String[] args) throws SQLException, FileNotFoundException, XMLStreamException {
        long id = 0;
        Address address = new Address();
        DBConnection connect = new DBConnection();
        AddressServiceImp addressImp = new AddressServiceImp();
        address = addressImp.getById(connect.Connect(), id);
        
        String street = address.GetStreet();
        String city = address.GetCity();
        String state = address.GetState();
        int zip = address.GetZip();
        System.out.print(id);
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
