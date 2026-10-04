package org.aston.module.controller.generators;

import org.aston.module.controller.builder.BusBuilder;
import org.aston.module.controller.validator.BusDataValidator;
import org.aston.module.dto.BusFromStorage;
import org.aston.module.interfaces.BusContract;
import org.aston.module.interfaces.BusStorageable;
import org.aston.module.interfaces.StorageDataTransferable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.stream.Collectors;

public class FromFileReader implements BusStorageable {
    private String filename;
    private BusDataValidator validator;
    private int length;

    public FromFileReader(String filename) {
        this.filename = filename;
        validator = new BusDataValidator();
    }

    public BusContract createBus(String info) {

        String[] parameters = info.split(";");
        String number = parameters[0];
        validator.validateNumber(number);
        String model = parameters[1];
        validator.validateModel(model);
        Float mileage = Float.valueOf(parameters[2]);
        validator.validateMileage(mileage);
        return new BusBuilder().setNumber(number)
                .setModel(model)
                .setMileage(mileage).build();
    }

    public Collection<BusContract> fillCollection(int length) {
        this.length = length;
        Collection<BusContract> buses = null;
        try {
            buses = Files.readAllLines(Paths.get(filename)).stream()
                    .map(line -> {return createBus(line);})
                    .limit(length)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buses;
    }

    @Override
    public StorageDataTransferable getData() throws IOException {
        return new BusFromStorage(fillCollection(length), length);
    }
}
