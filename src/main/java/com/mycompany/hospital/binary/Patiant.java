/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospital.binary;
import java.sql.JDBCType;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Andrew Gobert
 */
public class Patiant {
    private int idpatiant;
    private String fname;
    private String lname;
    private int home;
    private int mobile;
    
    public void AddPatiant(Connection con, int patiant, String first, String last, int address, int pcontact, int scontact, int doctor, int nurse, int room) throws SQLException{
        String query = "insert into patiants (fname, lname, address, home, mobile, pcontact, scontact, doctor, nurse, room) values ("
                + patiant + ", " + first + ", " + last + ", " + address + ", " + pcontact + ", " + scontact + ", " + doctor + ", " + nurse + ", " + room +");";
        Statement stmt = con.createStatement();
        stmt.execute(query);
    }
    public void GetPatiant(Connection con, int patiant){
        this.idpatiant = patiant;
    }
    public void UpdatePatiant(Connection con, String column, String patiant, String value) throws SQLException{
        String query = "update patiant set " + column + " = " + value + " where " + column + " = " + patiant + ";";
        Statement stmt = con.createStatement();
        stmt.execute(query);
    }
    public void DeletePatiant(Connection con, String column, String value) throws SQLException{
        String query = "delete from patiant where " + column + " = " + value + ";";
        Statement stmt = con.createStatement();
        stmt.executeQuery(query);
    }
    public String GetFName(){
        return this.fname;
    }
    public String GetLName(){
        return this.lname;
    }
    public int GetHome(){
        return this.home;
    }
    public int GetMobile(){
        return this.mobile;
    }
}
