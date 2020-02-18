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
}
