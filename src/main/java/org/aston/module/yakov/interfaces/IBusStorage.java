package org.aston.module.yakov.interfaces;

import java.io.IOException;
import java.util.Collection;

/**
 *
 * @author yakov
 */
public interface IBusStorage {
    public Collection<IBus> getData() throws IOException;
}
