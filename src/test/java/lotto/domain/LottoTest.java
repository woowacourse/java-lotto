package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoTest {

    private Lotto lotto;
    private final BonusNumber bonusNumber = new BonusNumber(7);

    @Test
    @DisplayName("보너스 번호가 포함되어 있으면 true를 반환한다.")
    void containsBonusNumber() {
        lotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));

        boolean containBonusNumber = lotto.containBonusNumber(bonusNumber);

        assertThat(containBonusNumber).isTrue();

    }

    @Test
    @DisplayName("보너스 번호가 포함되어 있지 않은 경우, false를 반환한다.")
    void doesNotContainBonusNumber() {
        lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        boolean containBonusNumber = lotto.containBonusNumber(bonusNumber);

        assertThat(containBonusNumber).isFalse();
    }

    @Test
    @DisplayName("포함된 당첨 번호의 개수를 반환한다.")
    void countWinningNumber() {
        lotto = new Lotto(List.of(5, 11, 15, 20, 26, 30));
        WinningNumber winningNumber = new WinningNumber(List.of(5, 10, 15, 20, 25, 30));

        int countWinningNumber = lotto.countWinningNumber(winningNumber);

        assertThat(countWinningNumber).isEqualTo(4);
    }
}
