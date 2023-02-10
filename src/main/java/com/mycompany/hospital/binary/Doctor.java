/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospital.binary;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Andrew Gobert
 */
public class Doctor {
    private int liscense;
    private String fname;
    private String lname;
    private int address;
    private int office;
    private int phone;
    
    public void AddDoctor(Connection con, int liscense, String fname, String lname, int address, int office, int phone) throws SQLException{
        String query = "insert into doctor (liscense, fname, lname, address, office, phone) values ("
                + liscense + ", " + fname + ", " + lname + ", " + address + ", " + office + ", " + phone + ");";
        Statement stmt = con.createStatement();
        stmt.executeQuery(query);
    }
    public void SetDoctor(int liscense, String fname, String lname, int address, int office, int phone){
        this.liscense = liscense;
        this.fname = fname;
        this.lname = lname;
        this.address = address;
        this.office = office;
        this.phone = phone;
    }
    public void RemDoctor(Connection con, String column, String value) throws SQLException{
        String query = "delete from doctor where " + column + " = " + value + ";";
        Statement stmt = con.createStatement();
        stmt.execute(query);
    }
    public void UpdateDoctor(Connection con, String column, String patiant, String value) throws SQLException{
        String query = "update doctor set " + column + " = " + value + " where " + column + " = " + patiant + ";";
        Statement stmt = con.createStatement();
        stmt.execute(query);
    }
    public String GetFName(){
        return this.fname;
    }
    public String GetLName(){
        return this.lname;
    }
    public int GetPhone(){
        return this.phone;
    }
    public int GetOffice(){
        return this.office;
    }
    public int GetLiscense(){
        return this.liscense;
    }
    public int GetAddress(){
        return this.address;
    }
}
