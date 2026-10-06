package org.aston.module.controller.generators;

import org.aston.module.controller.builder.BusBuilder;
import org.aston.module.interfaces.BusContract;
import org.aston.module.value.objects.Filename;
import org.aston.module.value.objects.Length;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.stream.Collectors;

public class FromFileReader extends BusCollectionGenerator {
    private Filename filename;


    public FromFileReader(Filename filename, Length length) throws IOException {
        super(length);
        if(fileExists(filename.getValue()))
            this.filename = filename;
        else
            throw new FileNotFoundException("Файл не существует!");
    }

    public boolean fileExists(String filename) {
        return Files.exists(Paths.get(filename));
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

    public Collection<BusContract> fillCollection() {
        Collection<BusContract> buses = null;
        try {
            buses = Files.readAllLines(Paths.get(filename.getValue())).stream()
                    .map(line -> {return createBus(line);})
                    .limit(length.getValue())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buses;
    }

}
