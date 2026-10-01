/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.aston.module.models;

import org.aston.module.interfaces.BusContract;

/**
 *
 * @author yakov
 */
public class Bus implements BusContract {

    private final String number;
    private final String model;
    private final Float mileage;

    public Bus(String number, String model, Float mileage) {
        this.number = number;
        this.model = model;
        this.mileage = mileage;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public String getNumber() {
        return number;
    }

    @Override
    public Float getMileage() {
        return mileage;
    }
}
