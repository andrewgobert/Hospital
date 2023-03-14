/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospital.dao.imp;

import com.mycompany.hospital.binary.Address;
import com.mycompany.hospital.dao.AddressDAO;
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
import javax.xml.stream.XMLStreamException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 *
 * @author Andrew Gobert
 */
public class AddressDAOImp extends AbstractDAO implements AddressDAO {
    
    private static final Logger logger = LogManager.getLogger(AddressDAOImp.class);
    
    private static final String CreateAddress = "insert into address (idaddress, number, street, city, state, zip) values (?, ?, ?, ?, ?, ?);";
    
    private static final String GetByID = "select * from address where idaddress = ?;";
    
    private static final String GetAll = "select * from address;";
    
    private static final String UpdateAddress = "update address set number = ?, street = ?, city = ?, state = ?, zip = ? where idaddress = ?";
    
    private static final String deleteAddress = "delete from address where idaddress = ?";
    
    @Override
    public boolean create (Address address){
        try{
            FileInputStream fis=new FileInputStream("db.properties");
            Properties p = new Properties();
            String dname= (String) p.get ("Dname"); 
            String url= (String) p.get ("url"); 
            String username= (String) p.get ("Uname"); 
            String password= (String) p.get ("password"); 
            Class.forName(dname);
            Connection con = DriverManager.getConnection(url + username + password);
            try(PreparedStatement preparedStatement = con.prepareStatement(CreateAddress)){
                con.setAutoCommit(false);
                preparedStatement.setInt(1, address.getNumber());
                preparedStatement.setString(2, address.getStreet());
                preparedStatement.setString(3, address.getCity());
                preparedStatement.setString(4, address.getState());
                preparedStatement.setInt(5, address.getZip());
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
            java.util.logging.Logger.getLogger(AddressDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddressDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddressDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    @Override
    public Address getById(long id) {
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
                int addressId = resultSet.getInt("addressId");
                int number = resultSet.getInt("number");
                String street = resultSet.getString("street");
                String city = resultSet.getString("city");
                String state = resultSet.getString("state");
                int zip = resultSet.getInt("zip");
                logger.info("Animal with ID " + id + " found: " + number + " " + street + " " + city);
                return new Address(addressId, number, street, city, state, zip);
            }
            else{
                logger.info("Address with id " + id + "was not found.");
                return null;
            }
        }
        catch (SQLException ex) {
            java.util.logging.Logger.getLogger(AddressDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddressDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(AddressDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public List<Address> getAll() {
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
                List<Address> animals = new ArrayList<>();
                while (resultSet.next()) {
                    int addressId = resultSet.getInt("idaddress");
                    int number = resultSet.getInt("number");
                    String street = resultSet.getString("street");
                    String city = resultSet.getString("city");
                    String state = resultSet.getString("state");
                    int zip = resultSet.getInt("zip");
                    animals.add(new Address(addressId, number, street, city, state, zip));
                }
                return animals;
            } catch (SQLException e) {
                logger.error("Get all addresses query failed");
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
            java.util.logging.Logger.getLogger(AddressDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddressDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(AddressDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     *
     * @param address
     * @return
     */
    @Override
    public boolean update(Address address) {
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
                preparedStatement = con.prepareStatement(UpdateAddress);
                con.setAutoCommit(false);
                preparedStatement.setInt(1, address.getNumber());
                preparedStatement.setString(2, address.getStreet());
                preparedStatement.setString(3, address.getCity());
                preparedStatement.setString(4, address.getState());
                preparedStatement.setInt(5, address.getZip());
                int result = preparedStatement.executeUpdate();
                con.commit();
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
            java.util.logging.Logger.getLogger(AddressDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddressDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(AddressDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    /**
     *
     * @param address
     * @return
     */
    @Override
    public boolean delete (Address address) {
        try{
            FileInputStream fis=new FileInputStream("C:\\Users\\Andrew Gobert\\Documents\\NetBeansProjects\\Hospital\\src\\main\\java\\com\\mycompany\\Resources\\db.properties");
            Properties p = new Properties();
            p.load (fis);
            String dname = (String) p.get ("Dname"); 
            String url = (String) p.get ("url"); 
            String username= (String) p.get ("username"); 
            String password= (String) p.get ("password");
            Connection con = DriverManager.getConnection(url + username + password);
            try(PreparedStatement preparedStatement = con.prepareStatement(deleteAddress)){
                con.setAutoCommit(false);
                preparedStatement.setInt(1, address.getNumber());
                preparedStatement.setString(2, address.getStreet());
                preparedStatement.setString(3, address.getCity());
                preparedStatement.setString(4, address.getState());
                preparedStatement.setInt(5, address.getZip());
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
            java.util.logging.Logger.getLogger(AddressDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddressDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(AddressDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}