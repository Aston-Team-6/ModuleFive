package org.aston.module.controller.writer;

import org.aston.module.interfaces.BusContract;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

public class BusFileWriter {

    public void writeCollection(
            Collection<BusContract> buses,
            String fileName) {

        try (PrintWriter writer =
                     new PrintWriter(new FileWriter(fileName, true))) {

            for (BusContract bus : buses) {
                writer.println(
                        "Number: " + bus.getNumber()
                                + ", Model: " + bus.getModel()
                                + ", Mileage: " + bus.getMileage()
                );
            }

        } catch (IOException e) {
            System.out.println(
                    "Ошибка записи в файл: " + e.getMessage()
            );
        }
    }
}