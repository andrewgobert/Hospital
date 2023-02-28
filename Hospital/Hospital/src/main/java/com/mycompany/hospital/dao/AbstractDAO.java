/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.hospital.dao;

import java.util.List;

/**
 *
 * @author Andrew Gobert
 * @param <T>
 */
public interface AbstractDAO<T> {
    boolean create(T t);

    T getById(long id);

    List<T> getAll();

    boolean update(T t);

    boolean delete(T t);
}
