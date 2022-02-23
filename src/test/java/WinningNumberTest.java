import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningNumberTest {

    private WinningNumber winningNumber;
    @BeforeEach
    public void setUp(){
        winningNumber = new WinningNumber(List.of(1, 2, 3, 4, 5, 6));
    }
    @Test
    @DisplayName("당첨 번호가 6자리 숫자인지 테스트")
    public void checkWinningNumberIs6Test() {
        assertThat(winningNumber.size()).isEqualTo(6);
    }

    @Test
    @DisplayName("당첨 번호에 보너스 볼이 있으면 예외 발생")
    public void checkBonusBallInWinningNumbersTest() {
        int bonusBall = 3;
        assertThatThrownBy(
                ()->winningNumber.checkBonusBall(bonusBall)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
