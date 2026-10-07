package org.aston.module.application.ports;

import java.io.IOException;

/**
 *
 * @author yakov
 */
public interface BusStorageable {
    public StorageDataTransferable getData() throws IOException;
}
