package lotto.domain;

import lotto.constant.WinningTier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class WinningLottoTest {

    @DisplayName("구매한 로또의 당첨 등수를 찾을 수 있다.")
    @ParameterizedTest
    @MethodSource("testArgs")
    void 구매한_로또의_당첨_등수를_찾을_수_있다(Lotto lotto, WinningTier expectedTier) {
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;
        WinningLotto winningLotto = new WinningLotto(new Lotto(winningNumbers), bonusNumber);

        assertThat(winningLotto.findWinningTier(lotto)).isEqualTo(expectedTier);
    }

    @DisplayName("보너스 번호가 1과 45 사이가 아닐 경우 예외가 발생한다.")
    @Test
    void 보너스_번호가_1과_45_사이가_아닐_경우_예외가_발생한다() {
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(winningNumbers);

        assertThatIllegalArgumentException().isThrownBy(() -> new WinningLotto(lotto, 0));
        assertThatIllegalArgumentException().isThrownBy(() -> new WinningLotto(lotto, 46));
    }

    @DisplayName("보너스 번호가 당첨 번호와 중복될 경우 예외가 발생한다.")
    @Test
    void 보너스_번호가_당첨_번호와_중복될_경우_예외가_발생한다() {
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(winningNumbers);
        int bonusNumber = 6;

        assertThatIllegalArgumentException().isThrownBy(() -> new WinningLotto(lotto, bonusNumber));
    }

    static Stream<Arguments> testArgs() {
        return Stream.of(
                Arguments.of(new Lotto(List.of(1, 2, 3, 7, 8, 9)), WinningTier.FIFTH),
                Arguments.of(new Lotto(List.of(1, 2, 3, 4, 7, 8)), WinningTier.FOURTH),
                Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 8)), WinningTier.THIRD),
                Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 7)), WinningTier.SECOND),
                Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)), WinningTier.FIRST)
        );
    }
}
