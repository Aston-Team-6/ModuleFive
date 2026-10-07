package  org.aston.module.tests;

import org.aston.module.presentation.validator.BusDataValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 *
 * @author yakov
 */
public class BusValidatorTest {

    @Test
    @DisplayName("successful validation the bus model")
    public void successValidateModel() {
        Assertions.assertDoesNotThrow(() -> {
            BusDataValidator validator = new BusDataValidator();

            validator.validateModel("model123");
        });
    }

    @Test
    @DisplayName("successful validation the bus number")
    public void successValidateNumber() {
        Assertions.assertDoesNotThrow(() -> {
            BusDataValidator validator = new BusDataValidator();

            validator.validateNumber("numberBus");
        });
    }

    @Test
    @DisplayName("successful validation the bus mileage")
    public void successValidateMileage() {
        Assertions.assertDoesNotThrow(() -> {
            BusDataValidator validator = new BusDataValidator();

            validator.validateMileage(Float.valueOf(200));
        });
    }

    @Test
    @DisplayName("fail validation the bus model")
    public void failValidateModel() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            BusDataValidator validator = new BusDataValidator();

            validator.validateModel(null);
        });
    }

    @Test
    @DisplayName("fail validation the bus number")
    public void failValidateNumber() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            BusDataValidator validator = new BusDataValidator();

            validator.validateNumber("");
        });
    }

    @Test
    @DisplayName("fail validation the bus mileage")
    public void failValidateMileage() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            BusDataValidator validator = new BusDataValidator();

            validator.validateMileage(Float.valueOf(-200));
        });
    }
}
