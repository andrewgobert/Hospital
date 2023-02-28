/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospital.binary;
import java.sql.*;
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
    private int address;
    private int pcontact;
    private int scontact;
    private int doctor;
    private int nurse;
    private int room;
    
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
    public int GetAddress(){
        return this.address;
    }
    public int GetHome(){
        return this.home;
    }
    public int GetMobile(){
        return this.mobile;
    }
    public int GetPContact(){
        return this.pcontact;
    }
    public int GetSContact(){
        return this.scontact;
    }
    public int GetDoctor(){
        return this.doctor;
    }
    public int GetNurse(){
        return this.nurse;
    }
    public int GetRoom(){
        return this.room;
    }

    public Patiant(int idpatiant, String fname, String lname, int home, int mobile, int address, int pcontact, int scontact, int doctor, int nurse, int room) {
        this.idpatiant = idpatiant;
        this.fname = fname;
        this.lname = lname;
        this.home = home;
        this.mobile = mobile;
        this.address = address;
        this.pcontact = pcontact;
        this.scontact = scontact;
        this.doctor = doctor;
        this.nurse = nurse;
        this.room = room;
    }
    
}
