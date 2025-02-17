package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningLottoFactoryTest {

    @DisplayName("보너스 번호가 숫자가 아닌 예외")
    @Test
    public void bonusBallNumber() {
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        String bonusBallInput = "칠";

        Lotto lotto = new Lotto(winningNumbers);
        WinningLottoFactory winningLottoFactory = new WinningLottoFactory();

        assertThatThrownBy(() -> winningLottoFactory.createWinningLotto(lotto, bonusBallInput))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
