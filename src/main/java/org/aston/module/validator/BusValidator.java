package org.aston.module.validator;

public class BusValidator {
    public static void validator(String numberBus, String modelBus, Float mileageBus) {
        validateNumber(numberBus);
        validateModel(modelBus);
        validateMileage(mileageBus);
    }
    private static void validateNumber(String numberBus) {
        if(numberBus == null || numberBus.isBlank() ) {
            throw new IllegalArgumentException("Введеный номер не прошел валидацию");
        }
    }

    private static void  validateModel(String modelBus) {
        if(modelBus == null || modelBus.isBlank()) {
            throw new IllegalArgumentException("Введеная модель не прошла валидацию");
        }
    }

    private static void validateMileage(Float mileageBus) {
        if(mileageBus == null){
            throw new IllegalArgumentException("Пробег содержит null");
        } else if(Float.isNaN(mileageBus) || Float.isInfinite(mileageBus) || mileageBus < 0) {
            throw new IllegalArgumentException("Пробег содержит невозможные значения");
        }
    }

}
