package lotto.domain;

import static lotto.domain.LottoRank.*;

import java.util.Map;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoResultTest {

    @ParameterizedTest
    @MethodSource
    @DisplayName("LottoResult 에 값이 추가가 되는가?")
    void Add_Rank_To_Result(LottoRank lottoRank, int value) {
        Map<LottoRank, Integer> RankMap = initLottoRankMap();
        RankMap.put(FIRST, 2);
        RankMap.put(SECOND, 1);
        LottoResult lottoResult = new LottoResult(RankMap);

        Assertions.assertThat(lottoResult.getResult().get(lottoRank)).isEqualTo(value);
    }

    private static Stream<Arguments> Add_Rank_To_Result() {
        return Stream.of(
                Arguments.arguments(FIRST, 2),
                Arguments.arguments(SECOND, 1),
                Arguments.arguments(THIRD, 0)
        );
    }

    @Test
    @DisplayName("수익률을 잘 계산하는가?")
    void Calculate_Yield() {
        Map<LottoRank, Integer> RankMap = initLottoRankMap();
        RankMap.put(THIRD, 1);
        RankMap.put(FOURTH, 1);
        RankMap.put(FIFTH, 1);

        int totalPrize = THIRD.getPrizeAmount() + FOURTH.getPrizeAmount() + FIFTH.getPrizeAmount();
        int moneyAmount = 10000;

        LottoResult lottoResult = new LottoResult(RankMap);
        double yield = lottoResult.calculateYield(new Money(moneyAmount));
        Assertions.assertThat(yield).isEqualTo(totalPrize / (double) moneyAmount);
    }
}
