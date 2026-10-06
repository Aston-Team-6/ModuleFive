package org.aston.module.controller.generators;

import org.aston.module.controller.validator.BusDataValidator;
import org.aston.module.dto.BusFromStorage;
import org.aston.module.interfaces.BusContract;
import org.aston.module.interfaces.BusStorageable;
import org.aston.module.interfaces.StorageDataTransferable;
import org.aston.module.value.objects.Length;

import java.io.IOException;
import java.util.Collection;

public abstract class BusCollectionGenerator implements BusStorageable {
    protected BusDataValidator validator;
    protected Length length;

    public BusCollectionGenerator(Length length) {
        this.length = length;
    }

    {
        validator = new BusDataValidator();
    }

    public Length getLength() {
        return length;
    }

    public abstract Collection<BusContract> fillCollection() throws IOException;

    @Override
    public StorageDataTransferable getData() throws IOException {
        return new BusFromStorage(fillCollection(), length.getValue());
    }
}
