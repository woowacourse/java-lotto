import enums.LottoRanking;
import model.*;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LottoWinningCheckerTest {

    @ParameterizedTest
    @MethodSource("lottoDataProvider")
    void 로또_결과를_확인할_수_있다(Numbers myNumbers, Numbers winningNumbers, int bonusNumber, LottoRanking ranking, int expectedCount) {
        //when
        Lotto lotto = new Lotto(myNumbers);
        Lotto winningNumbersLotto = new Lotto(winningNumbers);

        Lottos lottos = new Lottos();
        lottos.addLotto(lotto);

        WinningNumberWithBonusNumber winningNumberWithBonusNumber = new WinningNumberWithBonusNumber(winningNumbersLotto, bonusNumber);

        LottoResult lottoResult = LottoWinningChecker.calculateResult(lottos, winningNumberWithBonusNumber);
        //then
        assertThat(lottoResult.result().get(ranking)).isEqualTo(expectedCount);
    }

    private static Stream<Object[]> lottoDataProvider() {
        //given
        Numbers winningNumber = new Numbers(Arrays.asList(7, 8, 9, 10, 11, 12));
        int bonusNumber = 20;
        return Stream.of(

                new Object[]{
                        new Numbers(Arrays.asList(7, 8, 9, 10, 11, 12)),
                        winningNumber,
                        bonusNumber,
                        LottoRanking.FIRST,
                        1
                },
                new Object[]{
                        new Numbers(Arrays.asList(7, 8, 9, 10, 11, 20)),
                        winningNumber,
                        bonusNumber,
                        LottoRanking.SECOND,
                        1
                },
                new Object[]{
                        new Numbers(Arrays.asList(7, 8, 9, 10, 11, 6)),
                        winningNumber,
                        bonusNumber,
                        LottoRanking.THIRD,
                        1
                },
                new Object[]{
                        new Numbers(Arrays.asList(7, 8, 9, 10, 41, 42)),
                        winningNumber,
                        bonusNumber,
                        LottoRanking.FOURTH,
                        1
                },
                new Object[]{
                        new Numbers(Arrays.asList(7, 8, 9, 40, 41, 42)),
                        winningNumber,
                        bonusNumber,
                        LottoRanking.FIFTH,
                        1
                }

        );
    }
}
