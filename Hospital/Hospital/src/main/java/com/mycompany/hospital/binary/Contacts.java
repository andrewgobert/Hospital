/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospital.binary;

import com.mycompany.hospital.dao.ContactsDAO;
import java.util.List;

/**
 *
 * @author Andrew Gobert
 */
public abstract class Contacts implements ContactsDAO {
    private int idcontact;
    private String fname;
    private String lname;
    private int address;
    private int primary;
    private int mobile;

    public Contacts(int idcontact, String fname, String lname, int address, int primary, int mobile) {
        this.idcontact = idcontact;
        this.fname = fname;
        this.lname = lname;
        this.address = address;
        this.primary = primary;
        this.mobile = mobile;
    }
    
    public int getIDContact(){
        return this.idcontact;
    }
    public String getFName(){
        return this.fname;
    }
    public String getLName(){
        return this.lname;
    }
    public int getAddress(){
        return this.address;
    }
    public int getPrimary(){
        return this.primary;
    }
    public int getMobile(){
        return this.mobile;
    }

    /**
     *
     * @param id
     * @return 
     */

    public Contacts getById(long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     *
     * @return
     */
    @Override
    public List<Contacts> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean update(Address t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean delete(Address t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean create(Address t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean create(Contacts t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(Contacts t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(Contacts t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
