package org.aston.module.yakov.dto;

import java.util.Collection;

import org.aston.module.yakov.interfaces.IBus;

/**
 *
 * @author yakov
 */
public class OutputBusDto {

    public final Collection<IBus> busCollection;
    public final int busCount;

    public OutputBusDto(Collection<IBus> busCollection, int busCount) {
        this.busCollection = busCollection;
        this.busCount = busCount;
    }
}
