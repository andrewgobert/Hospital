/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.hospital.services;

import com.mycompany.hospital.binary.Doctor;

/**
 *
 * @author Andrew Gobert
 */
public interface DoctorService {
    Doctor getById(long id);
}
