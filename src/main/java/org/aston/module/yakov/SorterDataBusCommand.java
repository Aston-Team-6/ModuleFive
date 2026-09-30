package org.aston.module.yakov;

import java.io.IOException;

import org.aston.module.yakov.dto.OutputBusDto;
import org.aston.module.yakov.interfaces.factories.IBusSorterFactory;
import org.aston.module.yakov.interfaces.factories.IBusStorageFactory;

/**
 *
 * @author yakov
 */
public class SorterDataBusCommand {

    private final IBusStorageFactory busStorageFactory;
    private final IBusSorterFactory busSorterFactory;

    public SorterDataBusCommand(IBusStorageFactory busStorageFactory, IBusSorterFactory busSorterFactory) {
        this.busStorageFactory = busStorageFactory;
        this.busSorterFactory = busSorterFactory;
    }

    public OutputBusDto execute(StorageTypeEnum storageType, SorterTypeEnum sorterType) throws IOException {
        var BusDto = this.busStorageFactory
                .create(storageType)
                .getData();

        var busCollection = this.busSorterFactory
                .create(sorterType)
                .sortData(BusDto.getBusList());

        return new OutputBusDto(busCollection, BusDto.getCount());
    }
}
