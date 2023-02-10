/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospital.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.logging.*;
import org.apache.ibatis.logging.log4j.*;


import java.io.IOException;
import java.io.Reader;
import static java.util.logging.Level.ALL;
import java.util.logging.LogManager;
import org.slf4j.Logger;


/**
 *
 * @author Andrew Gobert
 */
public class MyBatisDaoFactory {
    private final static MyBatisDaoFactory myBatisDaoFactory = new MyBatisDaoFactory();
    private static SqlSessionFactory sqlSessionFactory;

    private MyBatisDaoFactory() {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}
