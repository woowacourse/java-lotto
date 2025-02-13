import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WinningLottoTest {

    @Test
    void 당첨번호와_보너스번호가_중복되는_경우_예외() {
        // given
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Number bonusNumber = new Number(1);

        // when & then
        assertThatThrownBy(() -> {
            new WinningLotto(winningNumbers, bonusNumber);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 번호의 매칭 개수를 확인할 수 있다.")
    @Test
    void test1() {
        // given
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Number bonus = new Number(7);
        Lotto lotto = new Lotto(List.of(4, 5, 6, 7, 44, 45));
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonus);

        // when
        final int matchedCount = winningLotto.getMatchedCount(lotto);

        // then
        assertThat(matchedCount).isEqualTo(3);
    }

    @DisplayName("보너스 번호의 매칭 여부를 확인할 수 있다.")
    @ParameterizedTest
    @CsvSource(value = {"6, true", "7, false"})
    void test2(int bonusNumber, boolean expected) {
        // given
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 45));
        Number bonus = new Number(bonusNumber);
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonus);

        // when
        final boolean matched = winningLotto.isMatchBonus(lotto);

        // then
        assertThat(matched).isEqualTo(expected);
    }
}