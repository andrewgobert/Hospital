/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospital.binary;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Andrew Gobert
 */
public class Office {
    private int room;
    private int floor;
    
    public void AddRoom(Connection con, int Room, int Floor) throws SQLException{
        String query = "insert into medicine (idmedicine, name, dose) values ("
                + Room + ", " + Floor + ");";
        Statement stmt = con.createStatement();
        stmt.executeQuery(query);
    }
    public void RemoveRoom(Connection con, int Room) throws SQLException{
        String query = "delete from medicine where room = "
                + Room + ");";
        Statement stmt = con.createStatement();
        stmt.executeQuery(query);
    }
    public int GetFloor(Connection con, int Room) throws SQLException{
        int Floor;
        String query = "select floor from medicine where room = "
                + Room + ");";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        Floor = rs.getInt("floor");
        return Floor;
    }
}
