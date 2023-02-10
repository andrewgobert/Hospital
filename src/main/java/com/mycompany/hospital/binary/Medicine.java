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
public class Medicine {
    private String name;
    private String dose;
    
    public void AddMedicine(Connection con, int id, String name, String dose) throws SQLException{
        String query = "insert into medicine (idmedicine, name, dose) values ("
                + id + ", " + name + ", " + dose + ");";
        Statement stmt = con.createStatement();
        stmt.executeQuery(query);
    }
    public void SetMedicine(String name, String dose){
        this.name = name;
        this.dose = dose;
    }
    public void RemMedicine(Connection con, String column, String value) throws SQLException{
        String query = "delete from medicine where " + column + " = " + value + ";";
        Statement stmt = con.createStatement();
        stmt.executeQuery(query);
    }
    public void UpdateMedicine(Connection con, String column, String patiant, String value) throws SQLException{
        String query = "update medicine set " + column + " = " + value + " where " + column + " = " + patiant + ";";
        Statement stmt = con.createStatement();
        stmt.executeQuery(query);
    }
    public String GetFName(){
        return this.name;
    }
    public String GetDose(){
        return this.dose;
    }
}
