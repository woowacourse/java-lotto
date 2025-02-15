import domain.Lotto;
import domain.LottoRanking;
import domain.LottoResult;
import domain.Lottos;
import domain.WinningNumberWithBonusNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import service.LottoWinningChecker;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LottoWinningCheckerTest {

    @ParameterizedTest
    @DisplayName("로또 결과를 확인할 수 있다")
    @MethodSource("lottoDataProvider")
    void 로또_결과를_확인할_수_있다(List<Integer> myNumbers, List<Integer> winningNumbers, int bonusNumber, LottoRanking ranking, int expectedCount) {
        Lotto lotto = new Lotto(myNumbers);
        Lotto winningNumbersLotto = new Lotto(winningNumbers);

        Lottos lottos = new Lottos();
        lottos.addLotto(lotto);

        WinningNumberWithBonusNumber winningNumberWithBonusNumber = new WinningNumberWithBonusNumber(winningNumbersLotto, bonusNumber);

        LottoResult lottoResult = LottoWinningChecker.calculateResult(lottos, winningNumberWithBonusNumber);

        assertThat(lottoResult.result().get(ranking)).isEqualTo(expectedCount);
    }

    private static Stream<Object[]> lottoDataProvider() {
        List<Integer> winningNumber = Arrays.asList(7, 8, 9, 10, 11, 12);
        int bonusNumber = 20;
        return Stream.of(

                new Object[]{
                        Arrays.asList(7, 8, 9, 10, 11, 12),
                        winningNumber,
                        bonusNumber,
                        LottoRanking.FIRST,
                        1
                },
                new Object[]{
                        Arrays.asList(7, 8, 9, 10, 11, 20),
                        winningNumber,
                        bonusNumber,
                        LottoRanking.SECOND,
                        1
                },
                new Object[]{
                        Arrays.asList(7, 8, 9, 10, 11, 6),
                        winningNumber,
                        bonusNumber,
                        LottoRanking.THIRD,
                        1
                },
                new Object[]{
                        Arrays.asList(7, 8, 9, 10, 41, 42),
                        winningNumber,
                        bonusNumber,
                        LottoRanking.FOURTH,
                        1
                },
                new Object[]{
                        Arrays.asList(7, 8, 9, 40, 41, 42),
                        winningNumber,
                        bonusNumber,
                        LottoRanking.FIFTH,
                        1
                }

        );
    }
}
