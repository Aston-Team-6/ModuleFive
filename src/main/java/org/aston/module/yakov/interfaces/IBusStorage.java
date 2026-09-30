package org.aston.module.yakov.interfaces;

import java.io.IOException;

import org.aston.module.yakov.interfaces.dto.IStorageDto;

/**
 *
 * @author yakov
 */
public interface IBusStorage {
    public IStorageDto getData() throws IOException;
}
