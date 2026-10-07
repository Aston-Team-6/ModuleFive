import org.aston.module.controller.builder.BusBuilder;
import org.aston.module.interfaces.BusContract;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
 
/**
 *
 * @author yakov
 */
public class BusBuilderTest {

    @Test
    public void succsessBuild() {
        BusBuilder busBuilder = new BusBuilder();

        var bus = busBuilder.setMileage(Float.valueOf(20))
            .setModel("raf4")
            .setNumber("NTH2234")
            .build();

        
        Assertions.assertInstanceOf(BusContract.class, bus);
        Assertions.assertEquals("raf4", bus.getModel());
        Assertions.assertEquals("NTH2234", bus.getNumber());
        Assertions.assertEquals(Float.valueOf(20), bus.getMileage());
    }

    @Test
    public void resetBuild() {
        BusBuilder busBuilder = new BusBuilder();

        busBuilder.setMileage(Float.valueOf(20))
            .setModel("raf4")
            .setNumber("NTH2234")
            .busReset();

        var bus = busBuilder.build();

        Assertions.assertNull(bus.getMileage());
        Assertions.assertNull(bus.getModel());
        Assertions.assertNull(bus.getNumber());
    }
}