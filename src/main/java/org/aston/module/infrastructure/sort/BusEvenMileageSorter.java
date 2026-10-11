package org.aston.module.infrastructure.sort;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.aston.module.application.ports.BusSorterable;
import org.aston.module.domain.ports.BusContract;
import org.aston.module.domain.values.collections.CustomList;

public class BusEvenMileageSorter implements BusSorterable {

    @Override
    public Collection<BusContract> sortData(Collection<BusContract> data) {

        CustomList<BusContract> result = new CustomList<>(data);

        for (int i = 0; i < result.size() - 1; i++) {

            // Если на этой позиции объект с нечётным пробегом,
            // позицию не трогаем.
            if (!isEven(result.get(i))) {
                continue;
            }

            int minIndex = i;

            // Ищем минимальный элемент только среди объектов
            // с чётным пробегом.
            for (int j = i + 1; j < result.size(); j++) {

                if (isEven(result.get(j))
                        && result.get(j).getMileage()
                        < result.get(minIndex).getMileage()) {

                    minIndex = j;
                }
            }

            // Меняем местами только объекты с чётным пробегом.
            if (minIndex != i) {
                BusContract temp = result.get(i);
                result.set(i, result.get(minIndex));
                result.set(minIndex, temp);
            }
        }

        return result;
    }

    private boolean isEven(BusContract bus) {
        return bus.getMileage() % 2 == 0;
    }
}
