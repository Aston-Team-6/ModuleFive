package org.aston.module.application.dto;

import java.util.Collection;

import org.aston.module.domain.ports.BusContract;


/**
 *
 * @author yakov
 */
public class OutputBus {
    public final Collection<BusContract> busCollection;
    public final int busCount;

    public OutputBus(Collection<BusContract> busCollection, int busCount) {
        this.busCollection = busCollection;
        this.busCount = busCount;
    }
}
