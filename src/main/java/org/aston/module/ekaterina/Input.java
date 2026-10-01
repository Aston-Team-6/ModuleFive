package org.aston.module;

import org.aston.module.yakov.interfaces.IBus;
import java.util.Collection;

public interface Input {
	IBus createBus(String... info);

	Collection<IBus> fillCollection(int length);
}
