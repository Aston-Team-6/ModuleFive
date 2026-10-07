package org.aston.module.infrastructure.dto;

import java.util.Collection;

import org.aston.module.application.ports.StorageDataTransferable;
import org.aston.module.domain.ports.BusContract;


/**
 *
 * @author yakov
 */
public class BusFromStorage implements StorageDataTransferable{
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
