package org.aston.module.interfaces.factories;

import org.aston.module.Enums.StorageTypeEnum;
import org.aston.module.interfaces.IBusStorage;

/**
 *
 * @author yakov
 */
public interface IBusStorageFactory {
    public IBusStorage create(StorageTypeEnum storageType);
}
