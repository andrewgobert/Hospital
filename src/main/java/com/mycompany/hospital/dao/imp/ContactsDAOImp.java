/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospital.dao.imp;

import com.mycompany.hospital.binary.Contacts;
import com.mycompany.hospital.dao.ContactsDAO;
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
public class ContactsDAOImp implements ContactsDAO {
    private static final Logger logger = LogManager.getLogger(ContactsDAOImp.class);
    
    private static final String CreateContact = "insert into contacts (idcontacts, fname, lname, address, primary, mobile) values (?, ?, ?, ?, ?, ?);";
    
    private static final String GetByID = "select * from contacts where idcontacts = ?;";
    
    private static final String GetAll = "select * from contacts;";
    
    private static final String UpdateContact = "update doctor set fname = ?, lname = ?, address = ?, primary = ?, mobile = ? where liscense = ?";
    
    private static final String DeleteContact = "delete from contact where idcontacts = ?";
    
    /**
     *
     * @param contact
     * @return
     * @throws SQLException
     */
    @Override
    public boolean create (Contacts contact){
        try{
            FileInputStream fis=new FileInputStream("C:\\Users\\Andrew Gobert\\Documents\\NetBeansProjects\\Hospital\\src\\main\\java\\com\\mycompany\\Resources\\db.properties");
            Properties p = new Properties();
            p.load (fis);
            String dname = (String) p.get ("Dname"); 
            String url = (String) p.get ("url"); 
            String username= (String) p.get ("username"); 
            String password= (String) p.get ("password");
            Connection con = DriverManager.getConnection(url + username + password);
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
        catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ContactsDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(ContactsDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ContactsDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    /**
     *
     * @param id
     * @return
     * @throws SQLException
     * @throws FileNotFoundException
     * @throws IOException
     */

    public Contacts getById(int id) throws SQLException, FileNotFoundException, IOException {
        FileInputStream fis=new FileInputStream("C:\\Users\\Andrew Gobert\\Documents\\NetBeansProjects\\Hospital\\src\\main\\java\\com\\mycompany\\Resources\\db.properties");
            Properties p = new Properties();
            p.load (fis);
            String dname = (String) p.get ("Dname"); 
            String url = (String) p.get ("url"); 
            String username= (String) p.get ("username"); 
            String password= (String) p.get ("password");
            Connection con = DriverManager.getConnection(url + username + password);
        try {
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
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ContactsDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        con.close();
        return null;
    }
    
    /**
     *
     * @return
     */
    @Override
    public List<Contacts> getAll() {
        try {
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
                List<Contacts> contacts = new ArrayList<>();
                while (resultSet.next()) {
                    int idcontacts = resultSet.getInt("idcontacts");
                    String fname = resultSet.getString("fname");
                    String lname = resultSet.getString("lname");
                    int address = resultSet.getInt("address");
                    int primary = resultSet.getInt("primary");
                    int mobile = resultSet.getInt("phone");
                    contacts.add(new Contacts(idcontacts, fname, lname, address, primary, mobile) {});
                }
                return contacts;
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
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ContactsDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(ContactsDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ContactsDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public boolean update(Contacts contact) {
        try {
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
                preparedStatement = con.prepareStatement(UpdateContact);
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
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ContactsDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(ContactsDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ContactsDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    @Override
    public boolean delete (Contacts contact){
        try{
            FileInputStream fis=new FileInputStream("C:\\Users\\Andrew Gobert\\Documents\\NetBeansProjects\\Hospital\\src\\main\\java\\com\\mycompany\\Resources\\db.properties");
            Properties p = new Properties();
            p.load (fis);
            String dname = (String) p.get ("Dname"); 
            String url = (String) p.get ("url"); 
            String username= (String) p.get ("username"); 
            String password= (String) p.get ("password");
            Connection con = DriverManager.getConnection(url + username + password);
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
        catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ContactsDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(ContactsDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ContactsDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
