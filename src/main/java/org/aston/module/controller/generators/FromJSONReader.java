package org.aston.module.controller.generators;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aston.module.dto.BusFromStorage;
import org.aston.module.interfaces.BusContract;
import org.aston.module.interfaces.BusStorageable;
import org.aston.module.interfaces.StorageDataTransferable;
import org.aston.module.models.Bus;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class FromJSONReader implements BusStorageable {
    private String filename;
    private int length;

    public FromJSONReader(String filename) {
        this.filename = filename;
    }
    public Collection<BusContract> fillCollection(int length) throws IOException {
        this.length =  length;
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(filename);
        Collection<BusContract> buses = mapper.readValue(file, new TypeReference<Collection<Bus>>(){});
        return buses;
    }
    @Override
    public StorageDataTransferable getData() throws IOException {
        return new BusFromStorage(fillCollection(length), length);
    }
}
