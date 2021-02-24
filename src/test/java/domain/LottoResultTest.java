package domain;

import domain.lotto.Lotto;
import domain.lotto.LottoBall;
import domain.lotto.LottoBundle;
import domain.result.LottoRank;
import domain.result.LottoResult;
import domain.result.WinningResult;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class LottoResultTest {
    private static LottoBundle lottoBundle;
    private static WinningResult winningResult;

    private LottoResult lottoResult;

    @BeforeAll
    static void setUp() {
        lottoBundle = makeLottoBundle();
        winningResult = makeWinningLotto();
    }

    private static LottoBundle makeLottoBundle() {
        return new LottoBundle(Arrays.asList(
                makeLotto(1, 6),
                makeLotto(3, 8)
        ));
    }

    private static WinningResult makeWinningLotto() {
        return new WinningResult(makeLotto(2, 7), LottoBall.valueOf(8));
    }

    private static Lotto makeLotto(final int from, final int to) {
        List<LottoBall> lotto = new ArrayList<>();
        for (int i = from; i <= to; i++) {
            lotto.add(LottoBall.valueOf(i));
        }
        return new Lotto(lotto);
    }

    @BeforeEach
    void LottoResultConstructor() {
        lottoResult = new LottoResult();
    }

    @DisplayName("checkResult 테스트")
    @Test
    void LottoResultCheckResult() {
        final Map<LottoRank, Integer> result = lottoResult.checkResult(lottoBundle, winningResult);
        assertThat(result.get(LottoRank.NONE_MATCHES)).isEqualTo(0);
        assertThat(result.get(LottoRank.THREE_MATCHES)).isEqualTo(0);
        assertThat(result.get(LottoRank.FOUR_MATCHES)).isEqualTo(0);
        assertThat(result.get(LottoRank.FIVE_MATCHES)).isEqualTo(1);
        assertThat(result.get(LottoRank.FIVE_AND_BONUS_MATCHES)).isEqualTo(1);
        assertThat(result.get(LottoRank.SIX_MATCHES)).isEqualTo(0);
    }

    @DisplayName("checkProfitRate 테스트")
    @Test
    void LottoResultCheckProfitRate() {
        final Map<LottoRank, Integer> result = lottoResult.checkResult(lottoBundle, winningResult);
        final double profitRate = lottoResult.checkProfitRate(result);

        final int moneyGain = LottoRank.FIVE_AND_BONUS_MATCHES.getPrizeMoney() + LottoRank.FIVE_MATCHES.getPrizeMoney();
        final int moneySpent = 2000;
        assertThat(profitRate).isEqualTo((double) moneyGain / (double) moneySpent);
    }
}
