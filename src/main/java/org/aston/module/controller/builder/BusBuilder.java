package org.aston.module.controller.builder;
import org.aston.module.interfaces.BuilderContract;
import org.aston.module.models.Bus;
import org.aston.module.controller.validator.BusValidator;
public class BusBuilder implements BuilderContract{
    private String number;
    private String model;
    private Float mileage;

    @Override
    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public void setMileage(Float mileage) {
        this.mileage = mileage;
    }

    public Bus busBuild() {
        BusValidator.validator(number, model, mileage);
        return new Bus(number, model, mileage);
    }

    public void busReset() {
        number = null;
        model = null;
        mileage = null;
    }
}
