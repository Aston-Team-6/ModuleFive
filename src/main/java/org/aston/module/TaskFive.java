package org.aston.module;

import org.aston.module.infrastructure.factories.SorterFactory;
import org.aston.module.infrastructure.factories.StorageFactory;
import org.aston.module.infrastructure.writers.BusFileWriter;
import org.aston.module.presentation.view.Menu;

/**
 *
 * @author yakov
 */
public class TaskFive {

    static void main(String[] args) {
        Menu menu = new Menu(new SorterFactory(), new StorageFactory(), new BusFileWriter());
        menu.start();
    }
}

