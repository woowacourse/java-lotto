package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinNumberTest {
    @Test
    @DisplayName("숫자가 아닌 값")
    void isNumberFormat() {
        assertThatThrownBy(() -> {
            String 당첨번호1 = "1, 3, 5, 7, a, 11";
            new WinNumber(당첨번호1);
        }).isInstanceOf(NumberFormatException.class)
                .hasMessage("숫자가 아니야");
    }

    @Test
    @DisplayName("당첨번호가 6개를 초과하는지")
    void isOverSix() {
        assertThatThrownBy(() -> {
            String 당첨번호2 = "1, 3, 5, 7, 9, 11, 13";
            new WinNumber(당첨번호2);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("숫자가 6개를 초과할 수 없습니다.");
    }

}
