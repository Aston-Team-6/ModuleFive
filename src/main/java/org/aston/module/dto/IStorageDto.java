package org.aston.module.dto;

import java.util.Collection;

import org.aston.module.interfaces.IBus;

/**
 *
 * @author yakov
 */
public interface IStorageDto {
    public Collection<IBus> getBusList();

    public int getCount();
}
