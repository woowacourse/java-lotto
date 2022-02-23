import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningNumberTest {

    private WinningNumber winningNumber;

    @Test
    @DisplayName("당첨 번호가 6자리가 아니면 예외 발생")
    public void checkWinningNumberIs6Test() {
        assertThatThrownBy(
                () -> new WinningNumber(List.of(1, 2, 3, 4, 5))).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("당첨 번호에 보너스 볼이 있으면 예외 발생")
    public void checkBonusBallInWinningNumbersTest() {
        winningNumber = new WinningNumber(List.of(1, 2, 3, 4, 5, 6));

        int bonusBall = 3;
        assertThatThrownBy(
                () -> winningNumber.checkBonusBall(bonusBall)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("당첨 번호에 1부터 45 이외의 숫자가 들어오면 예외 발생")
    public void checkAvailableRangeTest() {
        WinningNumber winningNumber = new WinningNumber(List.of(1, 2, 3, 4, 5, 46));
        assertThatThrownBy(winningNumber::checkAvailableRange).isInstanceOf(IllegalArgumentException.class);
    }
}
