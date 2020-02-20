package lotto.model;

import lotto.exception.NotNumberException;
import lotto.exception.NotSixNumbersException;
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
        }).isInstanceOf(NotNumberException.class)
                .hasMessage("숫자를 입력하셔야 합니다.");
    }

    @Test
    @DisplayName("당첨번호가 6개를 넘을때")
    void isOverSix() {
        assertThatThrownBy(() -> {
            String 당첨번호2 = "1, 3, 5, 7, 9, 11, 13";
            new WinNumber(당첨번호2);
        }).isInstanceOf(NotSixNumbersException.class)
                .hasMessage("6개의 숫자를 입력하셔야 합니다.");
    }

    @Test
    @DisplayName("당첨번호가 6개보다 부족할 때")
    void isUnderSix() {
        assertThatThrownBy(() -> {
            String 당첨번호2 = "1, 3, 5, 7, 9";
            new WinNumber(당첨번호2);
        }).isInstanceOf(NotSixNumbersException.class)
                .hasMessage("6개의 숫자를 입력하셔야 합니다.");
    }
}
