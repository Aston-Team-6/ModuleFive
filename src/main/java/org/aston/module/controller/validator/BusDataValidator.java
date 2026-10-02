package org.aston.module.controller.validator;

public class BusDataValidator {
    /*public static void validator(String numberBus, String modelBus, Float mileageBus) {
        validateNumber(numberBus);
        validateModel(modelBus);
        validateMileage(mileageBus);
    }*/
    public static void validateNumber(String numberBus) {
        if(numberBus == null || numberBus.isBlank() ) {
            throw new IllegalArgumentException("Введеный номер не прошел валидацию");
        }
    }

    public static void  validateModel(String modelBus) {
        if(modelBus == null || modelBus.isBlank()) {
            throw new IllegalArgumentException("Введеная модель не прошла валидацию");
        }
    }

    public static void validateMileage(Float mileageBus) {
        if(mileageBus == null){
            throw new IllegalArgumentException("Пробег содержит null");
        } else if(Float.isNaN(mileageBus) || Float.isInfinite(mileageBus) || mileageBus < 0) {
            throw new IllegalArgumentException("Пробег содержит невозможные значения");
        }
    }

}
