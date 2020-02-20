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
            String input = "1, 3, 5, 7, a, 11";
            new WinNumber(input);
        }).isInstanceOf(NotNumberException.class)
                .hasMessage("숫자를 입력하셔야 합니다.");
    }

    @Test
    @DisplayName("당첨번호 6개를 입력했는지")
    void isOverSix() {
        assertThatThrownBy(() -> {
            String input = "1, 3, 5, 7, 9, 11, 13";
            new WinNumber(input);
        }).isInstanceOf(NotSixNumbersException.class)
                .hasMessage("6개의 숫자를 입력하셔야 합니다.");
    }

}
