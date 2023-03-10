/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospital.services.imp;

import com.mycompany.hospital.binary.Contacts;
import com.mycompany.hospital.services.ContactService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 *
 * @author Andrew Gobert
 */
public class ContactsDAOImp implements ContactService {
    private static final Logger logger = LogManager.getLogger(AddressServiceImp.class);
    
    private static final String CreateContact = "insert into contacts (idcontacts, fname, lname, address, primary, mobile) values (?, ?, ?, ?, ?, ?);";
    
    private static final String GetByID = "select * from contacts where idcontacts = ?;";
    
    private static final String GetAll = "select * from contacts;";
    
    private static final String UpdateContact = "update doctor set fname = ?, lname = ?, address = ?, primary = ?, mobile = ? where liscense = ?";
    
    private static final String DeleteContact = "delete from contact where idcontacts = ?";
    
    public boolean create (Contacts contact) throws SQLException{
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/hospital?" + "user=root&password=nelson268");
        try(PreparedStatement preparedStatement = con.prepareStatement(CreateContact)){
            con.setAutoCommit(false);
            preparedStatement.setInt(1, contact.getIDContact());
            preparedStatement.setString(2, contact.getFName());
            preparedStatement.setString(3, contact.getLName());
            preparedStatement.setInt(4, contact.getAddress());
            preparedStatement.setInt(5, contact.getPrimary());
            preparedStatement.setInt(6, contact.getMobile());
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
    
    public Contacts getById(long id) {
        try {
            Connection con;
            con = DriverManager.getConnection("jdbc:mysql://localhost/hospital?" + "user=root&password=nelson268");
            PreparedStatement Statement = con.prepareStatement(GetByID);
            Statement.setLong(1, id);
            ResultSet resultSet = Statement.executeQuery();
            logger.info("Get Address by Id " + id);

            resultSet = Statement.executeQuery();
            
            if (resultSet.next()) {
                int idcontacts = resultSet.getInt("idcontacts");
                String fname = resultSet.getString("fname");
                String lname = resultSet.getString("lname");
                int address = resultSet.getInt("address");
                int primary = resultSet.getInt("primary");
                int mobile = resultSet.getInt("phone");
                logger.info("Animal with ID " + id + " found: " + fname + " " + lname + " " + address + " " + primary + " " + mobile);
                return new Contacts(idcontacts, fname, lname, address, primary, mobile) {};
            }
            else{
                logger.info("Address with id " + id + "was not found.");
                return null;
            }
            con.close();
            if (Statement != null) {
                Statement.close();
            } else {
            }
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ContactsDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<Contacts> getAll() throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/hospital?" + "user=root&password=nelson268");
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = con.prepareStatement(GetAll);
            resultSet = preparedStatement.executeQuery();
            List<Contacts> animals = new ArrayList<>();
            while (resultSet.next()) {
                int idcontacts = resultSet.getInt("idcontacts");
                String fname = resultSet.getString("fname");
                String lname = resultSet.getString("lname");
                int address = resultSet.getInt("address");
                int primary = resultSet.getInt("primary");
                int mobile = resultSet.getInt("phone");
                animals.add(new Contacts(idcontacts, fname, lname, address, primary, mobile) {});
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
    public boolean update(Contacts contact) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/hospital?" + "user=root&password=nelson268");
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(UpdateContact);
            connection.setAutoCommit(false);
            preparedStatement.setInt(1, contact.getIDContact());
            preparedStatement.setString(2, contact.getFName());
            preparedStatement.setString(3, contact.getLName());
            preparedStatement.setInt(4, contact.getAddress());
            preparedStatement.setInt(5, contact.getPrimary());
            preparedStatement.setInt(6, contact.getMobile());
            int result = preparedStatement.executeUpdate();
            connection.commit();
            return result > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.close();
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    
    public boolean delete (Contacts contact) throws SQLException{
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/hospital?" + "user=root&password=nelson268");
        try(PreparedStatement preparedStatement = con.prepareStatement(DeleteContact)){
            con.setAutoCommit(false);
            preparedStatement.setInt(1, contact.getIDContact());
            preparedStatement.setString(2, contact.getFName());
            preparedStatement.setString(3, contact.getLName());
            preparedStatement.setInt(4, contact.getAddress());
            preparedStatement.setInt(5, contact.getPrimary());
            preparedStatement.setInt(6, contact.getMobile());
            int result = preparedStatement.executeUpdate();
            con.commit();
            return result > 0;
        }
 finally {
            con.close();
        }
    }
}
