package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningLottoTest {

    @DisplayName("보너스 번호가 숫자가 아닌 예외")
    @Test
    public void bonusBallNumber() {
        String winningNumbersInput = "1, 2, 3, 4, 5, 6";
        String bonusBallInput = "a";
        Lotto lotto = new Lotto(winningNumbersInput);

        assertThatThrownBy(() ->  new WinningLotto(lotto, bonusBallInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호가 당첨 번호 6개와 중복되는 예외")
    @Test
    public void bonusBallDistinct() {
        String winningNumbersInput = "1, 2, 3, 4, 5, 6";
        String bonusBallInput = "6";
        Lotto lotto = new Lotto(winningNumbersInput);

        assertThatThrownBy(() ->  new WinningLotto(lotto, bonusBallInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호가 1-45의 범위가 아닌 예외")
    @Test
    public void bonusBallRange() {
        String winningNumbersInput = "1, 2, 3, 4, 5, 6";
        String bonusBallInput = "46";
        Lotto lotto = new Lotto(winningNumbersInput);

        assertThatThrownBy(() -> new WinningLotto(lotto, bonusBallInput))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
