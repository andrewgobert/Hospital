/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.hospital.dao;

/**
 *
 * @author Andrew Gobert
 * @param <T>
 */
public interface AbstractDAO<T> {
    void create(T t);

    T getById(Long id);
}
