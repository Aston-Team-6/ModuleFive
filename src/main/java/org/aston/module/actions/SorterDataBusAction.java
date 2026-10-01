package org.aston.module.actions;

import java.io.IOException;

import org.aston.module.dto.OutputBus;
import org.aston.module.interfaces.BusSorterable;
import org.aston.module.interfaces.BusStorageable;

/**
 *
 * @author yakov
 */
public class SorterDataBusAction {

    private final BusStorageable busStorage;
    private final BusSorterable busSorter;

    public SorterDataBusAction(BusStorageable busStorage, BusSorterable busSorter) {
        this.busStorage = busStorage;
        this.busSorter = busSorter;
    }

    public OutputBus execute() throws IOException {
        var BusDto = this.busStorage.getData();

        var busCollection = this.busSorter.sortData(BusDto.getBusList());

        return new OutputBus(busCollection, BusDto.getCount());
    }
}
