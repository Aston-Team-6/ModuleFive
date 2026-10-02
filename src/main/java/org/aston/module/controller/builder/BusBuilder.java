package org.aston.module.controller.builder;
import org.aston.module.models.Bus;

public class BusBuilder {
    private String number;
    private String model;
    private Float mileage;

    public BusBuilder setNumber(String number) {
        this.number = number;
        return this;
    }

    public BusBuilder setModel(String model) {
        this.model = model;
        return this;
    }

    public BusBuilder setMileage(Float mileage) {
        this.mileage = mileage;
        return this;
    }

    public Bus build() {
        // BusDataValidator.validator(number, model, mileage);
        return new Bus(number, model, mileage);
    }

    public void busReset() {
        number = null;
        model = null;
        mileage = null;
    }
}
