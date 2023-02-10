/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospital.util;

/**
 *
 * @author Andrew Gobert
 */

import java.lang.Object;

import java.io.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class DBConfigUtil {
    private final static Logger log = Logger.getLogger("log");

    private static Properties props = new Properties();
    
    private static Level WARN;

    static {
        try {
            props.load(new FileReader("db.properties"));
        } catch (FileNotFoundException e) {
            log.log(WARN, "File was not found");
        } catch (IOException e) {
            log.log(WARN, "Exception while loading properties file");
        }
    }

    private DBConfigUtil() {

    }

    public static Properties getProps() {
        return props;
    }
}