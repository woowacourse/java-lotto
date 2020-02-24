package lotto.model;

import lotto.exception.NotNumberException;
import lotto.exception.OverlapWinNumberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BonusBallTest {
    private WinNumber winNumber = new WinNumber(Arrays.asList(1, 3, 4, 44, 34, 13));
    @Test
    @DisplayName("입력한 보너스 볼 값이 당첨 번호와 중복될 경우")
    void isContainsWinNumber() {
        assertThatThrownBy(() -> {
            new BonusBall(winNumber, 1);
        }).isInstanceOf(OverlapWinNumberException.class)
        .hasMessage("당첨번호와 중복되는 숫자가 있습니다.");
    }
}
