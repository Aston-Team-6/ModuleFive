package org.aston.module.controller.generators;

import org.aston.module.controller.builder.BusBuilder;
import org.aston.module.interfaces.BusContract;
import org.aston.module.value.objects.Length;

import java.util.Collection;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RandomGenerator extends BusCollectionGenerator {
    private static Random rand;

    public RandomGenerator(Length length) {
        super(length);
    }

    {
        rand = new Random();
    }

    private String generateRouteNumber() {
        StringBuilder temp = new StringBuilder();
        char c = (char) rand.nextInt(50, 100);
        if (c == 'E' || c == 'M' || c == 'C' || c == 'T' || c == 'H') {
            temp.append(c);
        }
        int route = rand.nextInt(2500);
        temp.append(route);
        String number = temp.toString();
        validator.validateNumber(number);
        return number;
    }

    private String generateModel() {
        StringBuilder temp = new StringBuilder();
        int length = rand.nextInt(1, 5);
        for (int i = 0; i < length; i++) {
            char c = (char) rand.nextInt(65, 90);
            temp.append(c);
        }
        int modelNumber = rand.nextInt(9999);
        temp.append(modelNumber);
        String model = temp.toString().toUpperCase();
        validator.validateModel(model);
        return model;
    }

    private Float generateMileage() {
        Float mileage = rand.nextInt(0, 200000) + rand.nextFloat();
        validator.validateMileage(mileage);
        return mileage;
    }

    private BusContract createBus() {
        return new BusBuilder().setNumber(generateRouteNumber())
                .setModel(generateModel())
                .setMileage(generateMileage()).build();
    }

    public Collection<BusContract> fillCollection() {
        return Stream
                .generate(() -> createBus())
                .limit(length.getValue())
                .collect(Collectors.toList());
    }
}
