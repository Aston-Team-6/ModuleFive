package org.aston.module.application.ports;

import java.util.Collection;

import org.aston.module.domain.ports.BusContract;

/**
 *
 * @author yakov
 */
public interface StorageDataTransferable {
    public Collection<BusContract> getBusList();

    public int getCount();
}
