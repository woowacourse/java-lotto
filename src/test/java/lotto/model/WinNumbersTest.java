package lotto.model;

import lotto.exception.NotNumberException;
import lotto.exception.NotSixNumbersException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class WinNumbersTest {
    @Test
    @DisplayName("숫자가 아닌 값")
    void isNumberFormat() {
        assertThatThrownBy(() -> {
            String input = "1, 3, 5, 7, a, 11";
            new WinNumbers(input);
        }).isInstanceOf(NotNumberException.class)
                .hasMessage("숫자를 입력하셔야 합니다.");
    }

    @Test
    @DisplayName("당첨번호 6개를 입력했는지")
    void isOverSix() {
        assertThatThrownBy(() -> {
            String input = "1, 3, 5, 7, 9, 11, 13";
            new WinNumbers(input);
        }).isInstanceOf(NotSixNumbersException.class)
                .hasMessage("6개의 숫자를 입력하셔야 합니다.");
    }

    @Test
    @DisplayName("당첨번호안에 해당 숫자가 있을때")
    void isContainNumber_True() {
        WinNumbers winNumbers = new WinNumbers("1, 2, 3, 4, 5, 6");
        assertThat(winNumbers.isContainNumber(1)).isTrue();
    }

    @Test
    @DisplayName("당첨번호안에 해당 숫자가 없을때")
    void isContainNumber_False() {
        WinNumbers winNumbers = new WinNumbers("1, 2, 3, 4, 5, 6");
        assertThat(winNumbers.isContainNumber(7)).isFalse();
    }
}
