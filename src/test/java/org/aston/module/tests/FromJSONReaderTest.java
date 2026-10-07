package org.aston.module.tests;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.aston.module.controller.generators.FromJSONReader;
import org.aston.module.interfaces.BusContract;
import org.aston.module.value.objects.JsonFilename;
import org.aston.module.value.objects.Length;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class FromJSONReaderTest {

    FromJSONReader jsonReader;
    final String filename;
    int factSize;

    FromJSONReaderTest() throws IOException {
        filename = "src/test/resources/buses.json";
        jsonReader = new FromJSONReader(new JsonFilename(filename), new Length(40));
        factSize = 7;
    }

    @Test
    void fileExists() {
        boolean result = jsonReader.fileExists(filename);
        assertEquals(result, true);
    }

    @Test
    void getMapsFromJSON() throws IOException {
        List<Map<String, Object>> maps = jsonReader.getMapsFromJSON();
        assertEquals(maps.size(), factSize);
        assertEquals(maps.get(0).keySet().containsAll(Arrays.asList("number", "model", "mileage")), true);

    }

    @Test
    void createBus() throws IOException {
        List<Map<String, Object>> maps = jsonReader.getMapsFromJSON();
        BusContract bus = jsonReader.createBus((String) maps.get(0).get("number"),
                (String) maps.get(0).get("model"),
                Float.valueOf(maps.get(0).get("mileage").toString()));
        assertEquals(bus.getNumber(), "T454");
        assertEquals(bus.getModel(), "R343");
        assertEquals(bus.getMileage(), 22.0f);
    }

    @Test
    void fillCollection() throws IOException {
        List<BusContract> resulBuses = (List<BusContract>) jsonReader.fillCollection();
        BusContract bus = resulBuses.get(0);
        assertEquals(bus.getNumber(), "T454");
        assertEquals(bus.getModel(), "R343");
        assertEquals(bus.getMileage(), 22.0f);
        assertEquals(resulBuses.size(), factSize);
    }
}
