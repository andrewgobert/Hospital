/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospital.binary;

import com.mycompany.hospital.dao.AddressDAO;
import java.sql.Connection;

/**
 *
 * @author Andrew Gobert
 */
public class Address implements AddressDAO {
    private int address;
    private int number;
    private String street;
    private String city;
    private String state;
    private int zip;
    
    public void GetAddress(Connection con, int address){
        this.address = address;
    }
    
    public int GetNumber(){
        return this.number;
    }
    public String GetStreet(){
        return this.street;
    }
    public String GetCity(){
        return this.city;
    }
    public String GetState(){
        return this.state;
    }
    public int GetZip(){
        return this.zip;
    }

    @Override
    public void create(Address t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Address getById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
