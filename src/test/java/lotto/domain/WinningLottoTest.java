package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static lotto.constant.ErrorMessage.ERROR_WINNING_LOTTO_DUPLICATE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class WinningLottoTest {

    @Test
    @DisplayName("보너스 넘버가 당첨번호와 중복될 경우 예외를 발생시킨다")
    void throwExceptionWhenDuplicate() {
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 6;

        assertThatThrownBy(() -> new WinningLotto(winningNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_WINNING_LOTTO_DUPLICATE.message());
    }

    @Test
    @DisplayName("당첨번호와 일치하는 개수를 반환한다.")
    void countWinningMatched() {
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        Lotto lotto = new Lotto(new FixedLottoGenerator(List.of(1, 2, 3, 10, 11, 6)));

        assertThat(winningLotto.countWinningMatched(lotto)).isEqualTo(4);
    }

    @Test
    @DisplayName("보너스번호와 일치하면 true를 반환한다.")
    void isBonusMatched() {
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        Lotto lotto = new Lotto(new FixedLottoGenerator(List.of(1, 2, 3, 10, 7, 6)));

        assertThat(winningLotto.isBonusMatched(lotto)).isTrue();
    }
}
