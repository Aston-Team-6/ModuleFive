package org.aston.module.controller.generators;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aston.module.controller.builder.BusBuilder;
import org.aston.module.controller.validator.BusDataValidator;
import org.aston.module.dto.BusFromStorage;
import org.aston.module.interfaces.BusContract;
import org.aston.module.interfaces.BusStorageable;
import org.aston.module.interfaces.StorageDataTransferable;
import org.aston.module.value.objects.Length;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.stream.Collectors;

public class FromJSONReader implements BusStorageable {
    private String filename;
    private BusDataValidator validator;
    private Length length;

    public FromJSONReader(String filename, Length length) throws IOException {
        if(fileExists(filename)) {
            if (isValidFileFormat(filename, "json"))
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
    public BusContract createBus(String jsonLine) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        var reader = new StringReader(jsonLine);
        String routeNumber = null;
        String model = null;
        Float mileage = 0.f;
        try (JsonParser jsonParser = mapper.getFactory().createParser(reader)) {
            if (jsonParser.nextToken() != JsonToken.START_OBJECT) {
                throw new IOException("Неожиданный token!");
            }
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = jsonParser.currentName();
                jsonParser.nextToken();
                switch (fieldName) {
                    case "number":
                        routeNumber = jsonParser.getValueAsString();
                        validator.validateNumber(routeNumber);
                        break;
                    case "model":
                        model = jsonParser.getValueAsString();
                        validator.validateModel(model);
                        break;
                    case "mileage":
                        mileage = jsonParser.getFloatValue();
                        validator.validateMileage(mileage);
                        break;
                    default:
                        throw new IllegalStateException("Неожиданное значение: " + fieldName);
                }
            }
        }
        return new BusBuilder().setNumber(routeNumber)
                .setModel(model)
                .setMileage(mileage).build();
    }

    public Collection<BusContract> fillCollection() throws IOException {
        Collection<BusContract> buses = null;
        buses = Files.readAllLines(Paths.get(filename)).stream()
                .map(line -> {
                    if(line.startsWith("["))
                        line = line.substring(1);
                    try {
                        return createBus(line);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .limit(length.getValue())
                .collect(Collectors.toList());
        return buses;
    }

    @Override
    public StorageDataTransferable getData() throws IOException {
        return new BusFromStorage(fillCollection(), length.getValue());
    }
}
