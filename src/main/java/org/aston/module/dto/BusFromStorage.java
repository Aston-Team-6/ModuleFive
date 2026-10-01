package org.aston.module.dto;

import java.util.Collection;

import org.aston.module.interfaces.BusContract;
import org.aston.module.interfaces.StorageDataTransferable;

/**
 *
 * @author yakov
 */
public class BusFromStorage implements StorageDataTransferable {
    private final Collection<BusContract> busCollection;
    private final int count;

    public BusFromStorage(Collection<BusContract> busCollection, int count) {
        this.busCollection = busCollection;
        this.count = count;
    }

    @Override
    public Collection<BusContract> getBusList() {
        return busCollection;
    }

    @Override
    public int getCount() {
        return count;
    }
}
