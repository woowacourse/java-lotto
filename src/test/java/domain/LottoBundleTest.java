package domain;

import domain.lotto.Lotto;
import domain.lotto.LottoBall;
import domain.lotto.LottoBundle;
import domain.result.LottoRank;
import domain.result.LottoResult;
import domain.result.WinningResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class LottoBundleTest {
    private List<Lotto> lottos;
    private WinningResult winningResult;

    @BeforeEach
    void setUp() {
        lottos = new ArrayList<>();
        lottos.add(Lotto.of(Arrays.asList(1, 2, 3, 4, 5, 6)));
        lottos.add(Lotto.of(Arrays.asList(11, 12, 13, 14, 15, 16)));

        winningResult = new WinningResult(Lotto.of(Arrays.asList(1, 2, 3, 4, 5, 6)), LottoBall.valueOf(7));
    }

    @DisplayName("LottoBundle 생성 테스트")
    @Test
    void LottoBundleConstructorTest() {
        assertThatCode(() -> new LottoBundle(lottos))
                .doesNotThrowAnyException();
    }

    @DisplayName("LottoBundle countNumberOfLotto 테스트")
    @Test
    void countNumberOfLottoTest() {
        final LottoBundle lottoBundle = new LottoBundle(lottos);
        assertThat(lottoBundle.countNumberOfLotto()).isEqualTo(lottos.size());
    }

    @DisplayName("LottoBundle getLottoBundle 테스트")
    @Test
    void getLottoBundleTest() {
        final LottoBundle lottoBundle = new LottoBundle(lottos);
        assertThat(lottoBundle.getLottoBundle()).isEqualTo(lottos);
    }

    @DisplayName("LottoBundle checkResult 테스트")
    @Test
    void checkResultTest() {
        final LottoBundle lottoBundle = new LottoBundle(lottos);
        final LottoResult lottoResult = lottoBundle.checkResult(winningResult);
        final Map<LottoRank, Integer> lottoRankResult = lottoResult.getLottoResult();

        assertThat(lottoRankResult.get(LottoRank.SIX_MATCHES)).isEqualTo(1);
        assertThat(lottoRankResult.get(LottoRank.FIVE_AND_BONUS_MATCHES)).isEqualTo(0);
        assertThat(lottoRankResult.get(LottoRank.FIVE_MATCHES)).isEqualTo(0);
        assertThat(lottoRankResult.get(LottoRank.FOUR_MATCHES)).isEqualTo(0);
        assertThat(lottoRankResult.get(LottoRank.THREE_MATCHES)).isEqualTo(0);
        assertThat(lottoRankResult.get(LottoRank.NONE_MATCHES)).isEqualTo(1);
    }
}
