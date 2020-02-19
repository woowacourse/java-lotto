package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BonusBallTest {
    @Test
    @DisplayName("보너스 볼이 숫자가 아닐 때")
    void isNumberFormat() {
        assertThatThrownBy(() -> {
            new BonusBall("a");
        }).isInstanceOf(NumberFormatException.class)
        .hasMessage("숫자가 아닙니다.");
    }

    @Test
    void isContainsWinNumber() {
        WinNumber winNumber = new WinNumber("1, 3, 4, 44, 34, 13");
        assertThatThrownBy(() -> {
            new BonusBall("1");
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
