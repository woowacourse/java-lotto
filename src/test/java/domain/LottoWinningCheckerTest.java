package domain;

import fixture.LottoFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LottoWinningCheckerTest {

    private final LottoWinningChecker lottoWinningChecker = new LottoWinningChecker();

    private static Stream<Arguments> lottoDataProvider() {
        List<Integer> winningNumber = List.of(7, 8, 9, 10, 11, 12);
        int bonusNumber = 13;

        return Stream.of(
                Arguments.of(List.of(7, 8, 9, 10, 11, 12), winningNumber, bonusNumber, LottoRanking.FIRST, 1),
                Arguments.of(List.of(7, 8, 9, 10, 11, 13), winningNumber, bonusNumber, LottoRanking.SECOND, 1),
                Arguments.of(List.of(7, 8, 9, 10, 11, 45), winningNumber, bonusNumber, LottoRanking.THIRD, 1),
                Arguments.of(List.of(7, 8, 9, 10, 44, 45), winningNumber, bonusNumber, LottoRanking.FOURTH, 1),
                Arguments.of(List.of(7, 8, 9, 43, 44, 45), winningNumber, bonusNumber, LottoRanking.FIFTH, 1)
        );
    }

    @ParameterizedTest
    @DisplayName("로또 결과를 확인할 수 있다")
    @MethodSource("lottoDataProvider")
    void 로또_결과를_확인할_수_있다(List<Integer> myNumbers,
                         List<Integer> winningNumbers,
                         int bonusNumber,
                         LottoRanking ranking,
                         int expectedCount) {
        // given
        Lotto lotto = LottoFixture.createLotto(myNumbers);
        Lotto winningNumbersLotto = LottoFixture.createLotto(winningNumbers);

        Lottos lottos = Lottos.from(List.of(lotto));

        WinningNumberWithBonusNumber winningNumberWithBonusNumber
                = new WinningNumberWithBonusNumber(winningNumbersLotto, LottoNumber.from(bonusNumber));

        // when
        LottoResult lottoResult = lottoWinningChecker.calculateResult(lottos, winningNumberWithBonusNumber);

        // then
        assertThat(lottoResult.result().get(ranking))
                .isEqualTo(expectedCount);
    }
}
