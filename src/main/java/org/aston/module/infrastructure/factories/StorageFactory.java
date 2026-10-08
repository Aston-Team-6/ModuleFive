package org.aston.module.infrastructure.factories;
import java.io.IOException;

import org.aston.module.application.ports.BusStorageable;
import org.aston.module.domain.values.enums.StorageType;
import org.aston.module.domain.values.objects.JsonFilename;
import org.aston.module.domain.values.objects.Length;
import org.aston.module.infrastructure.generators.FromJSONReader;
import org.aston.module.infrastructure.generators.RandomGenerator;
import org.aston.module.infrastructure.generators.UsersInput;

public class StorageFactory {

    public BusStorageable create(StorageType type, Length len) {
        return switch (type) {
            case INPUT -> new UsersInput(len);
            case RANDOM -> new RandomGenerator(len);
            case FILE -> throw new IllegalArgumentException(
                    "Для FILE нужен путь — используйте create(type, len, path)");
        };
    }

    public BusStorageable create(StorageType type, Length len, String jsonPath)
            throws IOException {
        return switch (type) {
            case FILE -> new FromJSONReader(new JsonFilename(jsonPath), len);
            case INPUT -> new UsersInput(len);
            case RANDOM -> new RandomGenerator(len);
        };
    }
}