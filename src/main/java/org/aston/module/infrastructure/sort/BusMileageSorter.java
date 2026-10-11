package org.aston.module.infrastructure.sort;



import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.aston.module.application.ports.BusSorterable;
import org.aston.module.domain.ports.BusContract;
import org.aston.module.domain.values.collections.CustomList;

/**
 * Sorts buses by mileage in ascending order.
 */
public class BusMileageSorter implements BusSorterable {

    @Override
    public Collection<BusContract> sortData(Collection<BusContract> buscCollection) {
        CustomList<BusContract> buses = new CustomList<>(buscCollection);

        for (int i = 0; i < buses.size() - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < buses.size(); j++) {
                if (buses.get(j).getMileage().compareTo(buses.get(minIndex).getMileage()) < 0) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                BusContract temp = buses.get(i);
                buses.set(i, buses.get(minIndex));
                buses.set(minIndex, temp);
            }
        }

        return buses;
    }
}