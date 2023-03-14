/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.hospital.XMLParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author Andrew Gobert
 */
public class XMLParser {
    
    /**
     *
     * @throws FileNotFoundException
     * @throws XMLStreamException
     */
    public void event() throws FileNotFoundException, XMLStreamException {
        String filePath = "jdbc:mysql://localhost/hospital?";
        XMLInputFactory xmlInputFactory= XMLInputFactory.newInstance();
        XMLEventReader eventReader = xmlInputFactory.createXMLEventReader(new FileReader("data\\test.xml"));
    }
}
