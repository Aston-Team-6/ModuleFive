package org.aston.module.controller.generators;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aston.module.controller.builder.BusBuilder;
import org.aston.module.interfaces.BusContract;
import org.aston.module.value.objects.JsonFilename;
import org.aston.module.value.objects.Length;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FromJSONReader extends BusCollectionGenerator {
    private JsonFilename filename;

    public FromJSONReader(JsonFilename filename, Length length) throws IOException {
        super(length);
        if (fileExists(filename.getValue())) {
            this.filename = filename;
        }
        else {
            throw new FileNotFoundException("Файл не существует!");
        }
    }

    public boolean fileExists(String filename) {
        return Files.exists(Paths.get(filename));
    }

    public List<Map<String, Object>> getMapsFromJSON() throws IOException {
        File file = new File(filename.getValue());
        ObjectMapper mapper = new ObjectMapper();
       return mapper.readValue(file, new TypeReference<List<Map<String, Object>>>(){});
    }

    public BusContract createBus(String routeNumber, String model, Float mileage) {
        validator.validateNumber(routeNumber);
        validator.validateModel(model);
        validator.validateMileage(mileage);
        return new BusBuilder().setNumber(routeNumber)
                .setModel(model)
                .setMileage(mileage).build();
    }

    public Collection<BusContract> fillCollection() throws IOException {
        return getMapsFromJSON().stream()
                .map(item -> {
                    var fieldValues = item.values().toArray();
                    return createBus((String)fieldValues[0], (String)fieldValues[1], Float.valueOf(fieldValues[2].toString()));
                })
                .limit(length.getValue())
                .collect(Collectors.toList());
    }
}
