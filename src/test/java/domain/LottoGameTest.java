package domain;

import static common.TestUtils.createCountsDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class LottoGameTest {

    private static LottoReferee referee;
    private static final String firstPrizeLotto = "1, 2, 3, 4, 5, 6";
    private static final String secondPrizeLotto = "1, 2, 3, 4, 5, 7";
    private static final String fifthPrizeLotto = "1, 2, 3, 14, 15, 16";
    private static final String noPrizeLotto = "11, 12, 13, 14, 15, 16";

    @BeforeAll
    static void setup() {
        List<LottoNumber> winningNumbers = Stream
                .of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::of)
                .collect(Collectors.toList());

        LottoNumber bonusNumber = LottoNumber.of(7);

        referee = new LottoReferee(winningNumbers, bonusNumber);
    }

    @Test
    void getResultStatistics_initsWithLottosInput() {
        Lottos lottos = Lottos.of(getManualsList(firstPrizeLotto, secondPrizeLotto, noPrizeLotto),
                createCountsDto(3, 0));
        LottoGame game = new LottoGame(lottos, referee);

        Map<LottoResult, Integer> stats = game.getResultStatistics();

        assertThat(stats).containsOnlyKeys(LottoResult.values());
        assertThat(stats.get(LottoResult.FIRST)).isEqualTo(1);
        assertThat(stats.get(LottoResult.SECOND)).isEqualTo(1);
        assertThat(stats.get(LottoResult.THIRD)).isEqualTo(0);
        assertThat(stats.get(LottoResult.FOURTH)).isEqualTo(0);
        assertThat(stats.get(LottoResult.FIFTH)).isEqualTo(0);
    }

    @Test
    void getResultStatistics_exceptionOnModifyingStats() {
        Lottos lottos = Lottos.of(getManualsList(firstPrizeLotto, secondPrizeLotto, noPrizeLotto),
                createCountsDto(3, 0));
        LottoGame game = new LottoGame(lottos, referee);

        Map<LottoResult, Integer> stats = game.getResultStatistics();

        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> stats.put(LottoResult.FIRST, 10));
    }

    @Test
    void calculatePrizePriceRatio_zeroIfNoPrize() {
        Lottos lottos = Lottos.of(getManualsList(noPrizeLotto), createCountsDto(1, 0));
        LottoGame game = new LottoGame(lottos, referee);

        float actual = game.calculatePrizePriceRatio();

        assertThat(actual).isEqualTo(0f);
    }

    @Test
    void calculatePrizePriceRatio_fifthPrizeEqualsFiveTimesThePrice() {
        Lottos lottos = Lottos.of(getManualsList(fifthPrizeLotto), createCountsDto(1, 0));
        LottoGame game = new LottoGame(lottos, referee);

        float actual = game.calculatePrizePriceRatio();

        assertThat(actual)
                .isEqualTo((float) LottoResult.FIFTH.getPrize() / Lotto.PRICE);
    }

    private List<String> getManualsList(String... manualRaw) {
        List<String> lottosExample = new ArrayList<>();
        Collections.addAll(lottosExample, manualRaw);
        return lottosExample;
    }
}
