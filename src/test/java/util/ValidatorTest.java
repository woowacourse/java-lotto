package util;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ValidatorTest {

    @Test
    @DisplayName("음수 구매 금액 예외 테스트")
    public void validateNegativePriceTest() {
        int price = -4000;
        assertThatThrownBy(() -> {
            Validator.validateNegativePrice(price);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("음수");
    }

    @Test
    @DisplayName("유효한 구매 금액 확인 테스트")
    public void validatePositivePriceTest() {
        int price = 4000;
        assertDoesNotThrow(() -> {
            Validator.validateNegativePrice(price);
        });
    }

    @Test
    @DisplayName("수의 길이가 6개가 아닌 항목에 대한 예외 테스트")
    public void validateWrongLengthWinningNumberInputTest() {
        String[] winningNumbers = new String[]{"1", "2", "3", "4", "5"};
        assertThatThrownBy(() -> {
            Validator.validateWinningNumberInput(winningNumbers);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("6");
    }

    @Test
    @DisplayName("유효한 수 길이에 대한 확인 테스트")
    public void validateCorrectWinningNumberInputTest() {
        String[] winningNumbers = new String[]{"1", "2", "3", "4", "5", "6"};
        assertDoesNotThrow(() -> {
            Validator.validateWinningNumberInput(winningNumbers);
        });
    }

    @Test
    @DisplayName("숫자가 아닌 입력에 대한 예외 테스트")
    public void validateIntegerTest() {
        String input = "a";
        assertThatThrownBy(() -> {
            Validator.validateInteger(input);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("문자");
    }
}
