package lotto.utils;

import lotto.domain.errors.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NumberUtilsTest {
    @Test
    @DisplayName("정수가_아닌_값일_때")
    void parseNumberTest_not_integer() {
        String notNumberInput = "This is not number.";
        assertThatThrownBy(() -> NumberUtils.parseNumber(notNumberInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NOT_NUMBER
                        .getMessage());
    }

    @Test
    @DisplayName("정수일_때")
    void parseNumberTest_integer() {
        String notNumberInput = "3";
        NumberUtils.parseNumber(notNumberInput);
    }
}
