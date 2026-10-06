package org.aston.module.controller.generators;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aston.module.controller.builder.BusBuilder;
import org.aston.module.interfaces.BusContract;
import org.aston.module.value.objects.Filename;
import org.aston.module.value.objects.Length;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.stream.Collectors;

public class FromJSONReader extends BusCollectionGenerator {
    private Filename filename;

    public FromJSONReader(Filename filename, Length length) throws IOException {
        super(length);
        if (fileExists(filename.getValue()))
            this.filename = filename;
        else
            throw new FileNotFoundException("Файл не существует!");
    }

    public boolean fileExists(String filename) {
        return Files.exists(Paths.get(filename));
    }

    public BusContract createBus(String jsonLine) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        if (jsonLine.startsWith("["))
            jsonLine = jsonLine.substring(1);
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
                    case "number" -> {
                        routeNumber = jsonParser.getValueAsString();
                        validator.validateNumber(routeNumber);
                    }

                    case "model" -> {
                        model = jsonParser.getValueAsString();
                        validator.validateModel(model);
                    }

                    case "mileage" -> {
                        mileage = jsonParser.getFloatValue();
                        validator.validateMileage(mileage);
                    }
                    default -> throw new IllegalStateException("Неожиданное значение: " + fieldName);
                }
            }
        }
        return new BusBuilder().setNumber(routeNumber)
                .setModel(model)
                .setMileage(mileage).build();
    }

    public Collection<BusContract> fillCollection() throws IOException {
        Collection<BusContract> buses = null;
        buses = Files.readAllLines(Paths.get(filename.getValue())).stream()
                .map(line -> {
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

}
