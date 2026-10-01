package org.aston.module.interfaces;

import java.util.Collection;

/**
 *
 * @author yakov
 */
public interface IStorageDto {
    public Collection<IBus> getBusList();

    public int getCount();
}
