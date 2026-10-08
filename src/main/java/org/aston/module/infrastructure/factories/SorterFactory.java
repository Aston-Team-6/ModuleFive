package org.aston.module.infrastructure.factories;

import org.aston.module.application.ports.BusSorterable;
import org.aston.module.domain.values.enums.SorterType;
import org.aston.module.infrastructure.sort.BusEvenMileageSorter;
import org.aston.module.infrastructure.sort.BusMileageSorter;
import org.aston.module.infrastructure.sort.BusModelSorter;
import org.aston.module.infrastructure.sort.BusNumberSorter;

public class SorterFactory {

    public BusSorterable create(SorterType type) {
        return switch (type) {
            case MODULE -> new BusModelSorter();
            case NUMBER -> new BusNumberSorter();
            case MILEAGE -> new BusMileageSorter();
            case MILEAGE_ADDITIONAL -> new BusEvenMileageSorter();
        };
    }
}