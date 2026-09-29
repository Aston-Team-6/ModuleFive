package org.aston.module.yakov.interfaces;

import java.util.Collection;

import org.aston.module.yakov.Exceptions.ValidateException;

/**
 *
 * @author yakov
 */
public interface IBusValidator {
    public void validate(Collection<IBus> busCollection) throws ValidateException;
}
