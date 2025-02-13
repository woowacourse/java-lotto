package lotto.domain;

import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class WinningResultCalculatorTest {

    @Test
    void 당첨_결과_계산기가_정상적으로_생성된다() {
        // Given
        Lotto winningLotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = new LottoNumber(7);

        // When & Then
        Assertions.assertThatCode(() -> new WinningResultCalculator(winningLotto, bonusNumber))
                .doesNotThrowAnyException();
    }

    @Test
    void 당첨_로또와_보너스_번호에_중복된_번호가_있을_경우_예외가_발생한다() {
        // Given
        Lotto winningLotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = new LottoNumber(1);

        // When & Then
        Assertions.assertThatThrownBy(() -> new WinningResultCalculator(winningLotto, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호와 중복되지 않는 보너스 번호를 입력해 주세요.");
    }

    @Test
    void 당첨된_로또의_개수를_구한다() {
        // Given
        Lotto winningLotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = new LottoNumber(7);

        List<Lotto> lottos = List.of(
                new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)),
                new Lotto(Arrays.asList(7, 2, 3, 4, 5, 6)),
                new Lotto(Arrays.asList(8, 2, 3, 4, 5, 6)),
                new Lotto(Arrays.asList(8, 9, 3, 4, 5, 6))
        );
        WinningResultCalculator winningResultCalculator = new WinningResultCalculator(winningLotto, bonusNumber);

        // When
        WinningResult winningResult = winningResultCalculator.countLottoPrizes(lottos);
        Map<LottoAward, Integer> expectedResult = Map.of(LottoAward.FIRST_RANK, 1, LottoAward.SECOND_RANK, 1, LottoAward.THIRD_RANK, 1, LottoAward.FOURTH_RANK, 1, LottoAward.FIFTH_RANK, 0);

        // Then
        Assertions.assertThat(winningResult.getWinningResult()).isEqualTo(expectedResult);
    }
}
