/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospital.util;

import java.io.IOException;
import java.io.Reader;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import org.apache.tools.ant.types.resources.Resources;



/**
 *
 * @author Andrew Gobert
 */
public class MyBarisDaoFactory {
    private final static Logger log = Logger.getLogger("log");
    private final static MyBatisDaoFactory myBatisDaoFactory = new MyBatisDaoFactory();
    private static SqlSessionFactory sqlSessionFactory;

    private MyBarisDaoFactory() {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}
