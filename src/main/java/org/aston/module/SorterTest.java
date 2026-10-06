package org.aston.module;

import org.aston.module.interfaces.BusContract;
import org.aston.module.sort.BusMileageSorter;
import org.aston.module.sort.BusModelSorter;
import org.aston.module.sort.BusNumberSorter;
import org.aston.module.controller.builder.BusBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

public class SorterTest {

    public static void main(String[] args) {

        List<BusContract> buses = new ArrayList<>();

        buses.add(new BusBuilder()
                .setNumber("103")
                .setModel("Volvo")
                .setMileage(50000f)
                .build());

        buses.add(new BusBuilder()
                .setNumber("101")
                .setModel("Mercedes")
                .setMileage(20000f)
                .build());

        buses.add(new BusBuilder()
                .setNumber("102")
                .setModel("Ikarus")
                .setMileage(30000f)
                .build());

        System.out.println("=== ПО НОМЕРУ ===");

        Collection<BusContract> byNumber =
                new BusNumberSorter().sortData(buses);

        byNumber.forEach(bus ->
                System.out.println(
                        "Номер: " + bus.getNumber()
                                + ", модель: " + bus.getModel()
                                + ", пробег: " + bus.getMileage()
                )
        );


        System.out.println("=== ПО МОДЕЛИ ===");

        Collection<BusContract> byModel =
                new BusModelSorter().sortData(buses);

        byModel.forEach(bus ->
                System.out.println(
                        "Номер: " + bus.getNumber()
                                + ", модель: " + bus.getModel()
                                + ", пробег: " + bus.getMileage()
                )
        );

        System.out.println("=== ПО ПРОБЕГУ ===");

        Collection<BusContract> byMileage =
                new BusMileageSorter().sortData(buses);

        byMileage.forEach(bus ->
                System.out.println(
                        "Номер: " + bus.getNumber()
                                + ", модель: " + bus.getModel()
                                + ", пробег: " + bus.getMileage()
                )
        );
    }
}