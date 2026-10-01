package org.aston.module.interfaces;

import java.io.IOException;

/**
 *
 * @author yakov
 */
public interface IBusStorage {
    public IStorageDto getData() throws IOException;
}
