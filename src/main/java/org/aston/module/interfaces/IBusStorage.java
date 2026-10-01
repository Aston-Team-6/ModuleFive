package org.aston.module.interfaces;

import java.io.IOException;

import org.aston.module.interfaces.dto.IStorageDto;

/**
 *
 * @author yakov
 */
public interface IBusStorage {
    public IStorageDto getData() throws IOException;
}
