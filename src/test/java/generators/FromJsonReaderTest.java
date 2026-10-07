package generators;

import java.io.IOException;

import org.aston.module.controller.generators.FromJSONReader;
import org.aston.module.interfaces.BusStorageable;
import org.aston.module.interfaces.StorageDataTransferable;
import org.aston.module.value.objects.Filename;
import org.aston.module.value.objects.Length;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author yakov
 */
public class FromJsonReaderTest {
    private static final String SUCCESS_BUS_LIST = "src/test/resources/successBusList.json";
    private static final String BAD_FILE = "src/test/resources/badFile.json";

    private static final String FAIL_MILEAGE_BUS = "src/test/resources/failMileageBus.json";
    private static final String FAIL_NUMBER_BUS = "src/test/resources/failNumberBus.json";
    private static final String FAIL_MODEL_BUS = "src/test/resources/failModelBus.json";

    private static final int LENGTH = 3;

    @Test
    public void successGetCollectionFromJsonFile() throws IOException {
        Filename filename = new Filename(SUCCESS_BUS_LIST, "json");
        
        BusStorageable storage =  new FromJSONReader(filename, new Length(LENGTH));

        StorageDataTransferable dto = storage.getData();

        Assertions.assertEquals(dto.getCount(), LENGTH);

        Assertions.assertEquals(dto.getBusList().size(), dto.getCount());
    }

    @Test
    public void failedLoadBadFile() throws IOException {
        Filename filename = new Filename(BAD_FILE, "json");

        Assertions.assertThrows(IOException.class, () -> {
            BusStorageable storage =  new FromJSONReader(filename, new Length(LENGTH));

            storage.getData();
        });
    }

    

}
