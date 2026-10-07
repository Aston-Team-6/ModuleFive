package org.aston.module.application.ports;

import java.util.Collection;

import org.aston.module.domain.ports.BusContract;

/**
 *
 * @author yakov
 */
public interface BusSorterable {
    public Collection<BusContract> sortData(Collection<BusContract> buscCollection);
}
