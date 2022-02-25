package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningNumberTest {

    @Test
    @DisplayName("당첨 번호가 6자리가 아니면 예외 발생")
    public void checkWinningNumberIs6Test() {
        assertThatThrownBy(
                () -> new WinningNumber(List.of(1, 2, 3, 4, 5), 6)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("당첨 번호에 보너스 볼이 있으면 예외 발생")
    public void checkBonusBallInWinningNumbersTest() {
        int bonusBall = 3;
        assertThatThrownBy(
                () -> new WinningNumber(List.of(1, 2, 3, 4, 5, 6), bonusBall)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("당첨 번호에 1부터 45 이외의 숫자가 들어오면 예외 발생")
    public void checkAvailableRangeTest() {

        assertThatThrownBy(
                () -> new WinningNumber(List.of(1, 2, 3, 4, 5, 46), 6)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("당첨 번호에 중복된 숫자가 있으면 예외 발생")
    public void checkDuplicatedNumberTest() {

        assertThatThrownBy(
                () -> new WinningNumber(List.of(1, 1, 3, 4, 5, 46), 6)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
