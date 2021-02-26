package domain.result;

import domain.lotto.Lotto;
import domain.lotto.LottoBall;
import domain.lotto.LottoBundle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class LottoResultTest {
    private static LottoBundle lottoBundle;
    private static LottoBundle otherLottoBundle;
    private static WinningResult winningResult;

    @BeforeAll
    static void setUp() {
        lottoBundle = makeLottoBundle();
        otherLottoBundle = makeOtherLottoBundle();
        winningResult = makeWinningLotto();
    }

    private static LottoBundle makeLottoBundle() {
        return new LottoBundle(Arrays.asList(
                Lotto.of(Arrays.asList(1, 2, 3, 4, 5, 6)),
                Lotto.of(Arrays.asList(3, 4, 5, 6, 7, 8))));
    }

    private static LottoBundle makeOtherLottoBundle() {
        return new LottoBundle(Arrays.asList(
                Lotto.of(Arrays.asList(31, 2, 3, 4, 35, 36)),
                Lotto.of(Arrays.asList(33, 34, 5, 6, 7, 38))));
    }

    private static WinningResult makeWinningLotto() {
        return new WinningResult(
                Lotto.of(Arrays.asList(2, 3, 4, 5, 6, 7)),
                LottoBall.valueOf(8));
    }

    @DisplayName("checkResult 테스트")
    @Test
    void LottoResultCheckResult() {
        final LottoResult lottoResult = lottoBundle.checkResult(winningResult);
        final Map<LottoRank, Integer> result = lottoResult.getLottoResult();

        assertThat(result.get(LottoRank.NO_PRIZE)).isEqualTo(0);
        assertThat(result.get(LottoRank.FIFTH_PRIZE)).isEqualTo(0);
        assertThat(result.get(LottoRank.FOURTH_PRIZE)).isEqualTo(0);
        assertThat(result.get(LottoRank.THIRD_PRIZE)).isEqualTo(1);
        assertThat(result.get(LottoRank.SECOND_PRIZE)).isEqualTo(1);
        assertThat(result.get(LottoRank.FIRST_PRIZE)).isEqualTo(0);
    }

    @DisplayName("checkProfitRate 테스트")
    @Test
    void LottoResultCheckProfitRate() {
        final LottoResult lottoResult = lottoBundle.checkResult(winningResult);
        final double profitRate = lottoResult.checkProfitRate();

        final int moneyGain = LottoRank.SECOND_PRIZE.getPrizeMoney() + LottoRank.THIRD_PRIZE.getPrizeMoney();
        final int moneySpent = 2000;
        assertThat(profitRate).isEqualTo((double) moneyGain / (double) moneySpent);
    }

    @DisplayName("두 개의 LottoResult를 합쳐 하나의 LottoResult를 도출한다.")
    @Test
    void CombineResultTest() {
        final LottoResult lottoResult = lottoBundle.checkResult(winningResult);
        final LottoResult lottoResultToCombine = otherLottoBundle.checkResult(winningResult);
        lottoResult.combineResult(lottoResultToCombine);
        final Map<LottoRank, Integer> finalResult = lottoResult.getLottoResult();

        assertThat(finalResult.get(LottoRank.NO_PRIZE)).isEqualTo(0);
        assertThat(finalResult.get(LottoRank.FIFTH_PRIZE)).isEqualTo(2);
        assertThat(finalResult.get(LottoRank.FOURTH_PRIZE)).isEqualTo(0);
        assertThat(finalResult.get(LottoRank.THIRD_PRIZE)).isEqualTo(1);
        assertThat(finalResult.get(LottoRank.SECOND_PRIZE)).isEqualTo(1);
        assertThat(finalResult.get(LottoRank.FIRST_PRIZE)).isEqualTo(0);
    }
}
