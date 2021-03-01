package domain.lotto;

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
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class LottoBundleTest {
    private List<List<Integer>> lottos;
    private WinningResult winningResult;

    @BeforeEach
    void setUp() {
        lottos = new ArrayList<>();
        lottos.add(Arrays.asList(1, 2, 3, 4, 5, 6));
        lottos.add(Arrays.asList(11, 12, 13, 14, 15, 16));

        winningResult = new WinningResult(Lotto.of(Arrays.asList(1, 2, 3, 4, 5, 6)), LottoBall.valueOf(7));
    }

    @DisplayName("LottoBundle 생성 테스트")
    @Test
    void LottoBundleConstructorTest() {
        assertThatCode(() -> LottoBundle.of(lottos))
                .doesNotThrowAnyException();
    }

    @DisplayName("LottoBundle countNumberOfLotto 테스트")
    @Test
    void countNumberOfLottoTest() {
        final LottoBundle lottoBundle = LottoBundle.of(lottos);
        assertThat(lottoBundle.countNumberOfLotto()).isEqualTo(lottos.size());
    }

    @DisplayName("LottoBundle getLottoBundle 테스트")
    @Test
    void getLottoBundleTest() {
        final LottoBundle lottoBundle = LottoBundle.of(lottos);
        List<Lotto> testLottoBundle = lottos.stream()
                .map(numbers -> Lotto.of(numbers))
                .collect(Collectors.toList());
        assertThat(lottoBundle.getLottoBundle()).isEqualTo(testLottoBundle);
    }

    @DisplayName("LottoBundle checkResult 테스트")
    @Test
    void checkResultTest() {
        final LottoBundle lottoBundle = LottoBundle.of(lottos);
        final LottoResult lottoResult = lottoBundle.checkResult(winningResult);
        final Map<LottoRank, Integer> lottoRankResult = lottoResult.getLottoResult();

        assertThat(lottoRankResult.getOrDefault(LottoRank.FIRST_PRIZE, 0)).isEqualTo(1);
        assertThat(lottoRankResult.getOrDefault(LottoRank.SECOND_PRIZE, 0)).isEqualTo(0);
        assertThat(lottoRankResult.getOrDefault(LottoRank.THIRD_PRIZE, 0)).isEqualTo(0);
        assertThat(lottoRankResult.getOrDefault(LottoRank.FOURTH_PRIZE, 0)).isEqualTo(0);
        assertThat(lottoRankResult.getOrDefault(LottoRank.FIFTH_PRIZE, 0)).isEqualTo(0);
        assertThat(lottoRankResult.getOrDefault(LottoRank.NO_PRIZE, 0)).isEqualTo(1);
    }
}
