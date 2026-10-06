/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.aston.module.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.aston.module.interfaces.BusContract;

/**
 *
 * @author yakov
 */
public class Bus implements BusContract {

    private final String number;
    private final String model;
    private final Float mileage;
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Bus(@JsonProperty("number") String number,
               @JsonProperty("model") String model,
               @JsonProperty("mileage") Float mileage) {
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

    @Override
    public String toString() {
        return String.format("number: %s,\tmodel: %s\t, mileage: %.2f",number, model, mileage);
    }
}
