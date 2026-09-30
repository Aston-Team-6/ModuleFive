package org.aston.module.yakov.interfaces.dto;

import java.util.Collection;

import org.aston.module.yakov.interfaces.IBus;

/**
 *
 * @author yakov
 */
public interface IStorageDto {
    public Collection<IBus> getBusList();

    public int getCount();
}
