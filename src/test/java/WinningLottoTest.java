import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import domain.Lotto;
import domain.WinningLotto;
import domain.vo.Number;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WinningLottoTest {

    @DisplayName("당첨 번호와 보너스 번호가 중복되는 경우 예외가 발생한다.")
    @Test
    void duplicateWinningAndBonus() {
        // given
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Number bonusNumber = new Number(1);

        // when & then
        assertThatThrownBy(() -> {
            new WinningLotto(winningNumbers, bonusNumber);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 번호의 매칭 여부를 확인할 수 있다.")
    @ParameterizedTest
    @CsvSource(value = {"1, true", "2, true", "3, true", "4, true", "5, true", "6, true", "7, false"})
    void checkMatchedWinningNumber(int number, boolean expected) {
        // given
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Number bonus = new Number(7);
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonus);
        Number compare = new Number(number);

        // when
        final boolean isMatchWinningNumber = winningLotto.isMatchWinningNumber(compare);

        // then
        assertThat(isMatchWinningNumber).isEqualTo(expected);
    }

    @DisplayName("보너스 번호의 매칭 여부를 확인할 수 있다.")
    @ParameterizedTest
    @CsvSource(value = {"6, false", "7, true"})
    void checkMatchedBonusNumber(int number, boolean expected) {
        // given
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Number bonus = new Number(7);
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonus);
        Number compare = new Number(number);

        // when
        final boolean matched = winningLotto.isMatchBonus(compare);

        // then
        assertThat(matched).isEqualTo(expected);
    }
}
