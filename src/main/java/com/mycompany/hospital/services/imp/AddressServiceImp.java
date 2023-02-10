/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospital.services.imp;

import com.mycompany.hospital.binary.Address;
import com.mycompany.hospital.services.AddressService;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author Andrew Gobert
 */
public class AddressServiceImp implements AddressService {
    
    public Address getById() throws FileNotFoundException, XMLStreamException{
        String file = "C://Users//Andrew Gobert//Documents//NetBeansProjects//Hospital//src//main//java//com//mycompany//hospital//resources//Address.xml";
        Reader reader = new FileReader(file);
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLEventReader eventReader = xmlInputFactory.createXMLEventReader(reader);
        while(eventReader.hasNext()){
            XMLEvent event = eventReader.nextTag();
        }
        return null;
    }

    @Override
    public Address getById(long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
