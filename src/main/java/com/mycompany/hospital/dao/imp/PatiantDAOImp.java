/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospital.DAO.imp;

import com.mycompany.hospital.binary.Patiant;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 *
 * @author Andrew Gobert
 */
public class PatiantDAOImp {
    private static final Logger logger = LogManager.getLogger(PatiantDAOImp.class);
    
    private static final String CreatePatiant = "insert into patiant (idpatiants, fname, lname, address, home, phone, pcontact, scontact, doctor, nurse, room) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    
    private static final String GetByID = "select * from patiant where idpatiant = ?;";
    
    private static final String GetAll = "select * from patiant;";
    
    private static final String UpdatePatiant = "update patiant set fname = ?, lname = ?, address = ?, home = ?, mobile = ?, pcontact = ?, scontact = ?, doctor = ?, nurse = ?, room = ? where idpatiants = ?";
    
    private static final String DeletePatiant = "delete from patiant where idpatiant = ?";
    
    public boolean create (Patiant patiant) throws SQLException, FileNotFoundException, IOException{
        FileInputStream fis=new FileInputStream("C:\\Users\\Andrew Gobert\\Documents\\NetBeansProjects\\Hospital\\src\\main\\java\\com\\mycompany\\Resources\\db.properties");
        Properties p = new Properties();
        p.load (fis);
        String dname = (String) p.get ("Dname"); 
        String url = (String) p.get ("url"); 
        String username= (String) p.get ("username"); 
        String password= (String) p.get ("password");
        Connection con = DriverManager.getConnection(url + username + password);
        try(PreparedStatement preparedStatement = con.prepareStatement(CreatePatiant)){
            con.setAutoCommit(false);
            preparedStatement.setString(1, patiant.GetFName());
            preparedStatement.setString(2, patiant.GetLName());
            preparedStatement.setInt(3, patiant.GetAddress());
            preparedStatement.setInt(4, patiant.GetHome());
            preparedStatement.setInt(5, patiant.GetMobile());
            preparedStatement.setInt(6, patiant.GetPContact());
            preparedStatement.setInt(7, patiant.GetSContact());
            preparedStatement.setInt(8, patiant.GetDoctor());
            preparedStatement.setInt(9, patiant.GetNurse());
            preparedStatement.setInt(10, patiant.GetRoom());
            int result = preparedStatement.executeUpdate();
            con.commit();
            return result > 0;
        }
        catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            return false;
        } finally {
            con.close();
        }
    }
    
    public Patiant getById(int id) throws SQLException, FileNotFoundException, IOException {
        FileInputStream fis=new FileInputStream("C:\\Users\\Andrew Gobert\\Documents\\NetBeansProjects\\Hospital\\src\\main\\java\\com\\mycompany\\Resources\\db.properties");
        Properties p = new Properties();
        p.load (fis);
        String dname = (String) p.get ("Dname"); 
        String url = (String) p.get ("url"); 
        String username= (String) p.get ("username"); 
        String password= (String) p.get ("password");
        Connection con = DriverManager.getConnection(url, username, password);
        PreparedStatement Statement = con.prepareStatement(GetByID);
        Statement.setLong(1, id);
        ResultSet resultSet = Statement.executeQuery();
        logger.info("Get Address by Id " + id);
        resultSet = Statement.executeQuery();
        if (resultSet.next()) {
            int patiantId = resultSet.getInt("idpatiant");
            String fname = resultSet.getString("fname");
            String lname = resultSet.getString("lname");
            int address = resultSet.getInt("address");
            int home = resultSet.getInt("home");
            int mobile = resultSet.getInt("mobile");
            int pcontact = resultSet.getInt("pcontact");
            int scontact = resultSet.getInt("scontact");
            int doctor = resultSet.getInt("doctor");
            int nurse = resultSet.getInt("nurse");
            int room = resultSet.getInt("room");
            logger.info("Animal with ID " + id + " found: " + fname + " " + lname + " " + address + " " + home + " " + mobile + " " + pcontact + " " + scontact + " " + doctor + " " + nurse + " " + room);
            return new Patiant(id, fname, lname, home, mobile, address, pcontact, scontact, doctor, nurse, room);
        }
        else{
            logger.info("Address with id " + id + "was not found.");
            return null;
        }
    }
    
    public List<Patiant> getAll() throws SQLException, FileNotFoundException, IOException {
        FileInputStream fis=new FileInputStream("C:\\Users\\Andrew Gobert\\Documents\\NetBeansProjects\\Hospital\\src\\main\\java\\com\\mycompany\\Resources\\db.properties");
        Properties p = new Properties();
        p.load (fis);
        String dname = (String) p.get ("Dname"); 
        String url = (String) p.get ("url"); 
        String username= (String) p.get ("username"); 
        String password= (String) p.get ("password");
        Connection con = DriverManager.getConnection(url + username + password);
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = con.prepareStatement(GetAll);
            resultSet = preparedStatement.executeQuery();
            List<Patiant> patiants = new ArrayList<>();
            while (resultSet.next()) {
                int patiantId = resultSet.getInt("idpatiant");
                String fname = resultSet.getString("fname");
                String lname = resultSet.getString("lname");
                int address = resultSet.getInt("address");
                int home = resultSet.getInt("home");
                int mobile = resultSet.getInt("mobile");
                int pcontact = resultSet.getInt("pcontact");
                int scontact = resultSet.getInt("scontact");
                int doctor = resultSet.getInt("doctor");
                int nurse = resultSet.getInt("nurse");
                int room = resultSet.getInt("room");
                patiants.add(new Patiant(patiantId, fname, lname, home, mobile, address, pcontact, scontact, doctor, nurse, room));
            }
            return patiants;
        } catch (SQLException e) {
            logger.error("Get all patiants query failed");
            return null;
        } finally {
            
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public boolean update(Patiant patiant) throws SQLException, FileNotFoundException, IOException {
        FileInputStream fis=new FileInputStream("C:\\Users\\Andrew Gobert\\Documents\\NetBeansProjects\\Hospital\\src\\main\\java\\com\\mycompany\\Resources\\db.properties");
        Properties p = new Properties();
        p.load (fis);
        String dname = (String) p.get ("Dname"); 
        String url = (String) p.get ("url"); 
        String username= (String) p.get ("username"); 
        String password= (String) p.get ("password");
        Connection con = DriverManager.getConnection(url + username + password);
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = con.prepareStatement(UpdatePatiant);
            con.setAutoCommit(false);
            preparedStatement.setString(1, patiant.GetFName());
            preparedStatement.setString(2, patiant.GetLName());
            preparedStatement.setInt(3, patiant.GetAddress());
            preparedStatement.setInt(4, patiant.GetHome());
            preparedStatement.setInt(5, patiant.GetMobile());
            preparedStatement.setInt(6, patiant.GetPContact());
            preparedStatement.setInt(7, patiant.GetSContact());
            preparedStatement.setInt(8, patiant.GetDoctor());
            preparedStatement.setInt(9, patiant.GetNurse());
            preparedStatement.setInt(10, patiant.GetRoom());
            int result = preparedStatement.executeUpdate();
            con.commit();
            return result > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            con.close();
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    
    public boolean delete (Patiant patiant) throws SQLException, FileNotFoundException, IOException{
        FileInputStream fis=new FileInputStream("C:\\Users\\Andrew Gobert\\Documents\\NetBeansProjects\\Hospital\\src\\main\\java\\com\\mycompany\\Resources\\db.properties");
        Properties p = new Properties();
        p.load (fis);
        String dname = (String) p.get ("Dname"); 
        String url = (String) p.get ("url"); 
        String username= (String) p.get ("username"); 
        String password= (String) p.get ("password");
        Connection con = DriverManager.getConnection(url + username + password);
        try(PreparedStatement preparedStatement = con.prepareStatement(DeletePatiant)){
            con.setAutoCommit(false);
            preparedStatement.setString(1, patiant.GetFName());
            preparedStatement.setString(2, patiant.GetLName());
            preparedStatement.setInt(3, patiant.GetAddress());
            preparedStatement.setInt(4, patiant.GetHome());
            preparedStatement.setInt(5, patiant.GetMobile());
            preparedStatement.setInt(6, patiant.GetPContact());
            preparedStatement.setInt(7, patiant.GetSContact());
            preparedStatement.setInt(8, patiant.GetDoctor());
            preparedStatement.setInt(9, patiant.GetNurse());
            preparedStatement.setInt(10, patiant.GetRoom());
            int result = preparedStatement.executeUpdate();
            con.commit();
            return result > 0;
        }
        catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            return false;
        } finally {
            con.close();
        }
    }
}
