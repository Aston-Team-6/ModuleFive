package org.aston.module.yakov;

import java.io.IOException;

import org.aston.module.yakov.Exceptions.ValidateException;
import org.aston.module.yakov.interfaces.IBusValidator;
import org.aston.module.yakov.interfaces.factories.IBusSorterFactory;
import org.aston.module.yakov.interfaces.factories.IBusStorageFactory;
import org.aston.module.yakov.interfaces.factories.IBusViewFactory;

/**
 *
 * @author yakov
 */
public class SorterDataBusCommand {

    private final IBusStorageFactory busStorageFactory;
    private final IBusValidator validator;
    private final IBusSorterFactory busSorterFactory;
    private final IBusViewFactory busViewFactory;

    public SorterDataBusCommand(
            IBusStorageFactory busStorageFactory,
            IBusValidator validator,
            IBusSorterFactory busSorterFactory,
            IBusViewFactory busViewFactory
    ) {
        this.busStorageFactory = busStorageFactory;
        this.validator = validator;
        this.busSorterFactory = busSorterFactory;
        this.busViewFactory = busViewFactory;
    }

    public void execute(StorageTypeEnum storageType, SorterTypeEnum sorterType) {
        try {
            var busCollection = this.busStorageFactory
                    .create(storageType)
                    .getData();

            this.validator.validate(busCollection);

            busCollection = this.busSorterFactory
                .create(sorterType)
                .sortData(busCollection);

            this.busViewFactory
                .create()
                .output(busCollection);
        } catch (IOException e) {

            return;
        } catch (ValidateException e) {
            return;
        }
    }

}
