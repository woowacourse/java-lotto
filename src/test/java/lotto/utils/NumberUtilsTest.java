package lotto.utils;

import lotto.domain.errors.ErrorMessage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NumberUtilsTest {
    @Test
    void parseNumberTest_숫자가_아닌_값일_때() {
        String notNumberInput = "This is not number.";
        assertThatThrownBy(() -> NumberUtils.parseNumber(notNumberInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NOT_NUMBER.getMessage());
    }

    @Test
    void parseNumberTest_숫자일_때() {
        String notNumberInput = "3";
        NumberUtils.parseNumber(notNumberInput);
    }
}
