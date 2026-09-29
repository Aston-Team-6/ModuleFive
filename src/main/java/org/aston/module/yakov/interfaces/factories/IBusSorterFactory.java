package org.aston.module.yakov.interfaces.factories;

import org.aston.module.yakov.SorterTypeEnum;
import org.aston.module.yakov.interfaces.IBusSorter;

/**
 *
 * @author yakov
 */
public interface IBusSorterFactory {
    public IBusSorter create(SorterTypeEnum sorterType);
}
