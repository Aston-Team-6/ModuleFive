package org.aston.module.yakov.interfaces.factories;

import org.aston.module.yakov.StorageTypeEnum;
import org.aston.module.yakov.interfaces.IBusStorage;

/**
 *
 * @author yakov
 */
public interface IBusStorageFactory {
    public IBusStorage create(StorageTypeEnum storageType);
}
