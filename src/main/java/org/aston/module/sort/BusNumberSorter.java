package org.aston.module.sort;

import org.aston.module.interfaces.BusContract;
import org.aston.module.interfaces.BusSorterable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Sorts buses by number in ascending order.
 */
public class BusNumberSorter implements BusSorterable {

    @Override
    public Collection<BusContract> sortData(Collection<BusContract> buscCollection) {
        List<BusContract> buses = new ArrayList<>(buscCollection);

        for (int i = 0; i < buses.size() - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < buses.size(); j++) {
                if (buses.get(j).getNumber().compareTo(buses.get(minIndex).getNumber()) < 0) {
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
