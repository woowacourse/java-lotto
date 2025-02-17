package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Set;
import lotto.view.InputView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningLottoTest {

    @DisplayName("당첨 번호가 숫자가 아닌 예외")
    @Test
    public void winningNumbersDigit() {
        String bonusBallInput = "a";
        assertThatThrownBy(() -> InputView.validateAndParseBonusBall(bonusBallInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호가 숫자가 아닌 예외")
    @Test
    public void bonusBallDigit() {
        String bonusBallInput = "a";
        assertThatThrownBy(() -> InputView.validateAndParseBonusBall(bonusBallInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호가 당첨 번호 6개와 중복되는 예외")
    @Test
    public void bonusBallDistinct() {
        Set<Integer> winningNumbers = Set.of(1, 2, 3, 4, 5, 6);
        int bonusBall = 6;
        Lotto lotto = new Lotto(winningNumbers);

        assertThatThrownBy(() ->  new WinningLotto(lotto, bonusBall))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호가 1-45의 범위가 아닌 예외")
    @Test
    public void bonusBallRange() {
        Set<Integer> winningNumbers = Set.of(1, 2, 3, 4, 5, 6);
        int bonusBall = 46;
        Lotto lotto = new Lotto(winningNumbers);

        assertThatThrownBy(() -> new WinningLotto(lotto, bonusBall))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
