package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoResultTest {
    private static final BigDecimal FIRST_PRIZE = LottoRank.FIRST.multiplePrizeBy(1);
    private static final BigDecimal SECOND_PRIZE = LottoRank.SECOND.multiplePrizeBy(1);
    private static final BigDecimal THIRD_PRIZE = LottoRank.THIRD.multiplePrizeBy(1);

    @Test
    @DisplayName("당첨결과 금액 총합 계산 테스트")
    void summarizeLottoPrize() {
        LottoResult lottoResult = new LottoResult(
                List.of(LottoRank.FIRST, LottoRank.FIRST, LottoRank.SECOND, LottoRank.THIRD));
        BigDecimal expected = FIRST_PRIZE.multiply(BigDecimal.valueOf(2)).add(SECOND_PRIZE).add(THIRD_PRIZE);
        assertThat(lottoResult.getProfitRate(new Budget(4000)))
                .isEqualTo(expected.divide(BigDecimal.valueOf(4000), 2, RoundingMode.HALF_DOWN));
    }

    @Test
    @DisplayName("당첨 결과 순위 갯수 조회 테스트")
    void summarizeCountByLottoRank() {
        LottoResult lottoResult = new LottoResult(
                List.of(LottoRank.FIRST, LottoRank.FIRST, LottoRank.SECOND, LottoRank.THIRD));

        Map<LottoRank, Integer> resultMap = lottoResult.getResultMap();
        assertAll("countRank",
                () -> assertThat(resultMap.get(LottoRank.FIRST)).isEqualTo(2),
                () -> assertThat(resultMap.get(LottoRank.SECOND)).isEqualTo(1),
                () -> assertThat(resultMap.get(LottoRank.THIRD)).isEqualTo(1),
                () -> assertThat(resultMap.get(LottoRank.FOURTH)).isEqualTo(0),
                () -> assertThat(resultMap.get(LottoRank.FIFTH)).isEqualTo(0),
                () -> assertThat(resultMap.get(LottoRank.NOTHING)).isEqualTo(0)
        );
    }
}
