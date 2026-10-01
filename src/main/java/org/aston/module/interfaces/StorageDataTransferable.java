package org.aston.module.interfaces;

import java.util.Collection;

/**
 *
 * @author yakov
 */
public interface StorageDataTransferable {
    public Collection<BusContract> getBusList();

    public int getCount();
}
