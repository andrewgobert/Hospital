/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospital.DAO.imp;

import com.mycompany.hospital.binary.Doctor;
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
import java.util.logging.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 *
 * @author Andrew Gobert
 */
public class DoctorDAOImp {
    private static final Logger logger = LogManager.getLogger(DoctorDAOImp.class);
    
    private static final String CreateDoctor = "insert into doctor (liscense, fname, lname, phone, address, office) values (?, ?, ?, ?, ?, ?);";
    
    private static final String GetByID = "select * from doctor where liscense = ?;";
    
    private static final String GetAll = "select * from doctor;";
    
    private static final String UpdateDoctor = "update doctor set fname = ?, lname = ?, phone = ?, address = ?, office = ? where liscense = ?";
    
    private static final String DeleteDoctor = "delete from doctor where liscense = ?";
    
    public boolean create (Doctor doctor) throws SQLException, FileNotFoundException, IOException{
        FileInputStream fis=new FileInputStream("C:\\Users\\Andrew Gobert\\Documents\\NetBeansProjects\\Hospital\\src\\main\\java\\com\\mycompany\\Resources\\db.properties");
        Properties p = new Properties();
        p.load (fis);
        String dname = (String) p.get ("Dname"); 
        String url = (String) p.get ("url"); 
        String username= (String) p.get ("username"); 
        String password= (String) p.get ("password");
        Connection con = DriverManager.getConnection(url, username, password);
        try(PreparedStatement preparedStatement = con.prepareStatement(CreateDoctor)){
            con.setAutoCommit(false);
            preparedStatement.setInt(1, doctor.GetLiscense());
            preparedStatement.setString(2, doctor.GetFName());
            preparedStatement.setString(3, doctor.GetLName());
            preparedStatement.setInt(4, doctor.GetOffice());
            preparedStatement.setInt(5, doctor.GetPhone());
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
    
    public Doctor getById(long id) throws FileNotFoundException, IOException {
        try {
            FileInputStream fis=new FileInputStream("C:\\Users\\Andrew Gobert\\Documents\\NetBeansProjects\\Hospital\\src\\main\\java\\com\\mycompany\\Resources\\db.properties");
            Properties p = new Properties();
            p.load (fis);
            String dname = (String) p.get ("Dname"); 
            String url = (String) p.get ("url"); 
            String username= (String) p.get ("username"); 
            String password= (String) p.get ("password");
            Connection con = DriverManager.getConnection(url + username + password);
            PreparedStatement Statement = con.prepareStatement(GetByID);
            Statement.setLong(1, id);
            ResultSet resultSet = Statement.executeQuery();
            logger.info("Get Address by Id " + id);

            resultSet = Statement.executeQuery();
            
            if (resultSet.next()) {
                int liscense = resultSet.getInt("liscense");
                String fname = resultSet.getString("fname");
                String lname = resultSet.getString("lname");
                int address = resultSet.getInt("address");
                int office = resultSet.getInt("office");
                int phone = resultSet.getInt("phone");
                logger.info("Animal with ID " + id + " found: " + fname + " " + lname + " " + address + " " + office + " " + phone);
                return new Doctor(liscense, fname, lname, address, office, phone);
            }
            else{
                logger.info("Address with id " + id + "was not found.");
                return null;
            }
        }
        catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DoctorDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<Doctor> getAll() throws SQLException, FileNotFoundException, IOException {
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
            List<Doctor> animals = new ArrayList<>();
            while (resultSet.next()) {
                int liscense = resultSet.getInt("liscense");
                String fname = resultSet.getString("fname");
                String lname = resultSet.getString("lname");
                int address = resultSet.getInt("address");
                int office = resultSet.getInt("office");
                int phone = resultSet.getInt("phone");
                animals.add(new Doctor(liscense, fname, lname, address, office, phone));
            }
            return animals;
        } catch (SQLException e) {
            logger.error("Get all doctors query failed");
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
    public boolean update(Doctor doctor) throws SQLException, FileNotFoundException, IOException {
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
            preparedStatement = con.prepareStatement(UpdateDoctor);
            con.setAutoCommit(false);
            preparedStatement.setInt(1, doctor.GetLiscense());
            preparedStatement.setString(2, doctor.GetFName());
            preparedStatement.setString(3, doctor.GetLName());
            preparedStatement.setInt(4, doctor.GetOffice());
            preparedStatement.setInt(5, doctor.GetPhone());
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
    
    public boolean delete (Doctor doctor) throws SQLException, FileNotFoundException, IOException{
        FileInputStream fis=new FileInputStream("C:\\Users\\Andrew Gobert\\Documents\\NetBeansProjects\\Hospital\\src\\main\\java\\com\\mycompany\\Resources\\db.properties");
        Properties p = new Properties();
        p.load (fis);
        String dname = (String) p.get ("Dname"); 
        String url = (String) p.get ("url"); 
        String username= (String) p.get ("username"); 
        String password= (String) p.get ("password");
        Connection con = DriverManager.getConnection(url + username + password);
        try(PreparedStatement preparedStatement = con.prepareStatement(DeleteDoctor)){
            con.setAutoCommit(false);
            preparedStatement.setInt(1, doctor.GetLiscense());
            preparedStatement.setString(2, doctor.GetFName());
            preparedStatement.setString(3, doctor.GetLName());
            preparedStatement.setInt(4, doctor.GetOffice());
            preparedStatement.setInt(5, doctor.GetPhone());
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
