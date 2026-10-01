package org.aston.module.interfaces.factories;

import org.aston.module.enums.SorterTypeEnum;
import org.aston.module.interfaces.IBusSorter;

/**
 *
 * @author yakov
 */
public interface IBusSorterFactory {
    public IBusSorter create(SorterTypeEnum sorterType);
}
