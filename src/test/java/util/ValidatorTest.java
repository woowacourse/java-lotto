package util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

public class ValidatorTest {

    @Test
    public void validateNegativePriceTest() {
        int price = -4000;
        assertThatThrownBy(() -> {
            Validator.validateNegativeInteger(price);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void validatePositivePriceTest() {
        int price = 4000;
        assertDoesNotThrow(() -> {
            Validator.validateNegativeInteger(price);
        });
    }

    @Test
    public void validateWrongLengthLottoNumberInputTest() {
        String[] winningNumbers = new String[]{"1", "2", "3", "4", "5"};
        assertThatThrownBy(() -> {
            Validator.validateLottoNumbers(winningNumbers);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void validateCorrectLottoNumberInputTest() {
        String[] winningNumbers = new String[]{"1", "2", "3", "4", "5", "6"};
        assertDoesNotThrow(() -> {
            Validator.validateLottoNumbers(winningNumbers);
        });
    }


    @Test
    public void validateIntegerTest() {
        String input = "a";
        assertThatThrownBy(() -> {
            Validator.validateInteger(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
