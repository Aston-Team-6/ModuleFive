
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

    @Override
    public String toString() {
        return String.format("number: %s,\tmodel: %s\t, mileage: %.2f", number, model, mileage);
    }
}
