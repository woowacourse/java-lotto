package lotto.model;

import lotto.exception.NotNumberException;
import lotto.exception.OverlapWinNumberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BonusBallNoTest {
    private WinNumbers winNumbers = new WinNumbers("1, 2, 3, 4, 5, 6");

    @Test
    @DisplayName("보너스 볼이 숫자가 아닐 때")
    void isNumberFormat() {
        assertThatThrownBy(() -> {
            new BonusBallNo("a", winNumbers);
        }).isInstanceOf(NotNumberException.class)
        .hasMessage("숫자가 아닙니다.");
    }

    @Test
    @DisplayName("당첨번호와 중복이 되는지 확인")
    void isContainsWinNumber() {
        assertThatThrownBy(() -> {
            new BonusBallNo("1", winNumbers);
        }).isInstanceOf(OverlapWinNumberException.class);
    }
}
