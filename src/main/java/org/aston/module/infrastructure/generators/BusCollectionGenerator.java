package org.aston.module.infrastructure.generators;


import java.io.IOException;
import java.util.Collection;

import org.aston.module.application.ports.BusStorageable;
import org.aston.module.application.ports.StorageDataTransferable;
import org.aston.module.domain.ports.BusContract;
import org.aston.module.domain.values.objects.Length;
import org.aston.module.infrastructure.dto.BusFromStorage;
import org.aston.module.presentation.validator.BusDataValidator;

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
