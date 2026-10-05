package org.aston.module.controller.generators;

import org.aston.module.controller.builder.BusBuilder;
import org.aston.module.controller.validator.BusDataValidator;
import org.aston.module.dto.BusFromStorage;
import org.aston.module.interfaces.BusContract;
import org.aston.module.interfaces.BusStorageable;
import org.aston.module.interfaces.StorageDataTransferable;
import org.aston.module.value.objects.Length;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.stream.Collectors;

public class FromFileReader implements BusStorageable {
    private String filename;
    private BusDataValidator validator;
    private Length length;

    public FromFileReader(String filename, Length length) throws IOException {
        if(fileExists(filename)) {
            if (isValidFileFormat(filename, "csv"))
                this.filename = filename;
            else
                throw new IOException("Неверный формат файла!");
        }else
            throw new FileNotFoundException("Файл не существует!");
        this.length = length;
    }

    {
        validator = new BusDataValidator();
    }

    public boolean fileExists(String filename) {
        return Files.exists(Paths.get(filename));
    }

    public boolean isValidFileFormat(String filename, String fileExtension) throws IOException {
        int dotIndex = filename.lastIndexOf('.');
        if(dotIndex != -1) {
            String extension = filename.substring(dotIndex + 1);
            return extension.equals(fileExtension.toUpperCase());
        }
        throw new IOException("Неверный формат файла!");
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
            buses = Files.readAllLines(Paths.get(filename)).stream()
                    .map(line -> {return createBus(line);})
                    .limit(length.getValue())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buses;
    }

    @Override
    public StorageDataTransferable getData() throws IOException {
        return new BusFromStorage(fillCollection(), length.getValue());
    }
}
