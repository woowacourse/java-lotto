package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class RankTest {

    private static final WinningLotto WINNING_LOTTO
        = WinningLotto.of(Arrays.asList(1, 2, 3, 4, 5, 6), 7);

    private static final List<Lotto> LOTTO_GROUP = new ArrayList<>();

    @ParameterizedTest
    @MethodSource("generateLottoStaticResult")
    @DisplayName("당첨 확인 테스트")
    void match_1stLotto_1stRank(LottoStatisticResult result, Rank rank) {

        // when
        Long count = result.get(rank);

        // then
        assertThat(count).isEqualTo(1L);
    }

    private static Stream<Arguments> generateLottoStaticResult() {
        LottoStatisticResult firstRankResult = generateLottoStatisticResult(
            Lotto.fromNumbers(Arrays.asList(1, 2, 3, 4, 5, 6)));
        LottoStatisticResult secondRankResult = generateLottoStatisticResult(
            Lotto.fromNumbers(Arrays.asList(1, 2, 3, 4, 5, 7)));
        LottoStatisticResult thirdRankResult = generateLottoStatisticResult(
            Lotto.fromNumbers(Arrays.asList(1, 2, 3, 4, 5, 45)));
        LottoStatisticResult fourthRankResult = generateLottoStatisticResult(
            Lotto.fromNumbers(Arrays.asList(1, 2, 3, 4, 44, 45)));
        LottoStatisticResult fifthRankResult = generateLottoStatisticResult(
            Lotto.fromNumbers(Arrays.asList(1, 2, 3, 43, 44, 45)));
        LottoStatisticResult nothingRankResult = generateLottoStatisticResult(
            Lotto.fromNumbers(Arrays.asList(1, 2, 42, 43, 44, 45)));

        return Stream.of(
            Arguments.of(firstRankResult, Rank.FIRST),
            Arguments.of(secondRankResult, Rank.SECOND),
            Arguments.of(thirdRankResult, Rank.THIRD),
            Arguments.of(fourthRankResult, Rank.FOURTH),
            Arguments.of(fifthRankResult, Rank.FIFTH),
            Arguments.of(nothingRankResult, Rank.NOTHING)
        );
    }

    private static LottoStatisticResult generateLottoStatisticResult(Lotto lotto) {
        LOTTO_GROUP.clear();
        LOTTO_GROUP.add(lotto);

        Lottos lottos = new Lottos(LOTTO_GROUP);

        return Rank.match(lottos, WINNING_LOTTO);
    }
}
