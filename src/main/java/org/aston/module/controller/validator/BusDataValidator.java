package org.aston.module.controller.validator;

public class BusDataValidator {

    public void validateNumber(String numberBus) throws IllegalArgumentException {
        if (numberBus == null || numberBus.isBlank()) {
            throw new IllegalArgumentException("Введеный номер не прошел валидацию");
        }
    }

    public void validateModel(String modelBus) throws IllegalArgumentException {
        if (modelBus == null || modelBus.isBlank()) {
            throw new IllegalArgumentException("Введеная модель не прошла валидацию");
        }
    }

    public void validateMileage(Float mileageBus) throws IllegalArgumentException {
        if (mileageBus == null) {
            throw new IllegalArgumentException("Пробег содержит null");
        } else if (Float.isNaN(mileageBus) || Float.isInfinite(mileageBus) || mileageBus < 0) {
            throw new IllegalArgumentException("Пробег содержит невозможные значения");
        }
    }

}
