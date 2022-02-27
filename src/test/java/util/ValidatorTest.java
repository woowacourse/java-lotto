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
            Validator.validateNegativePrice(price);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("음수");
    }

    @Test
    public void validatePositivePriceTest() {
        int price = 4000;
        assertDoesNotThrow(() -> {
            Validator.validateNegativePrice(price);
        });
    }

    @Test
    public void validateWrongLengthWinningNumberInputTest() {
        String[] winningNumbers = new String[]{"1", "2", "3", "4", "5"};
        assertThatThrownBy(() -> {
            Validator.validateWinningNumberInput(winningNumbers);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("6");
    }

    @Test
    public void validateCorrectWinningNumberInputTest() {
        String[] winningNumbers = new String[]{"1", "2", "3", "4", "5", "6"};
        assertDoesNotThrow(() -> {
            Validator.validateWinningNumberInput(winningNumbers);
        });
    }

    @Test
    public void validateIntegerTest() {
        String input = "a";
        assertThatThrownBy(() -> {
            Validator.validateInteger(input);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("문자");
    }
}
