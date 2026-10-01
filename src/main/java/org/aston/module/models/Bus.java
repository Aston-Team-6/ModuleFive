/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.aston.module.models;

import org.aston.module.interfaces.IBus;

/**
 *
 * @author yakov
 */
public class Bus implements IBus {

    private String number;
    private String model;
    private Float mileage;

    public Bus(String Number, String model, Float mileage) {
        this.number = Number;
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
