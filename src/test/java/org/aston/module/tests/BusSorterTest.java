import java.util.ArrayList;
import java.util.Collection;

import org.aston.module.controller.sort.BusMileageSorter;
import org.aston.module.controller.sort.BusModelSorter;
import org.aston.module.controller.sort.BusNumberSorter;
import org.aston.module.interfaces.BusContract;
import org.aston.module.interfaces.BusSorterable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 *
 * @author yakov
 */
public class BusSorterTest {

    @Test
    @DisplayName("success sort bus by mileage")
    public void BusMileageSorterTest() {
        BusSorterable sorter = new BusMileageSorter();

        Collection<BusContract> busCollection = new ArrayList<>();

        var bus3 = Mockito.mock(BusContract.class);
        Mockito.when(bus3.getMileage()).thenReturn(Float.valueOf(15));

        var bus1 = Mockito.mock(BusContract.class);
        Mockito.when(bus1.getMileage()).thenReturn(Float.valueOf(10)); 

        var bus2 = Mockito.mock(BusContract.class);
        Mockito.when(bus2.getMileage()).thenReturn(Float.valueOf(12));  

        busCollection.add(bus1);
        busCollection.add(bus2);
        busCollection.add(bus3);

        var sortedBusCollection = sorter.sortData(busCollection);
        
        Assertions.assertEquals(sortedBusCollection.isEmpty(), false);

        Assertions.assertEquals(sortedBusCollection.toArray()[0], bus1);
    }

    @Test
    @DisplayName("success sort bus by model")
    public void BusModelSorterTest() {
        BusSorterable sorter = new BusModelSorter();

        Collection<BusContract> busCollection = new ArrayList<>();

        var bus3 = Mockito.mock(BusContract.class);
        Mockito.when(bus3.getModel()).thenReturn("NTH123");

        var bus1 = Mockito.mock(BusContract.class);
        Mockito.when(bus1.getModel()).thenReturn("ABC333"); 

        var bus2 = Mockito.mock(BusContract.class);
        Mockito.when(bus2.getModel()).thenReturn("XYZ987");  

        busCollection.add(bus1);
        busCollection.add(bus2);
        busCollection.add(bus3);

        var sortedBusCollection = sorter.sortData(busCollection);
        
        Assertions.assertEquals(sortedBusCollection.isEmpty(), false);

        Assertions.assertEquals(sortedBusCollection.toArray()[sortedBusCollection.size() - 1], bus2);
    }

    @Test
    @DisplayName("success sort bus by number")
    public void BusNumberSorterTest() {
        BusSorterable sorter = new BusNumberSorter();

        Collection<BusContract> busCollection = new ArrayList<>();

        var bus3 = Mockito.mock(BusContract.class);
        Mockito.when(bus3.getNumber()).thenReturn("NTH123");

        var bus1 = Mockito.mock(BusContract.class);
        Mockito.when(bus1.getNumber()).thenReturn("ABC333"); 

        var bus2 = Mockito.mock(BusContract.class);
        Mockito.when(bus2.getNumber()).thenReturn("XYZ987");  

        busCollection.add(bus1);
        busCollection.add(bus2);
        busCollection.add(bus3);

        var sortedBusCollection = sorter.sortData(busCollection);
        
        Assertions.assertEquals(sortedBusCollection.isEmpty(), false);

        Assertions.assertEquals(sortedBusCollection.toArray()[0], bus1);
    }
}
