package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoResultTest {
    private static final Money FIRST_PRIZE = LottoRank.FIRST.multiplePrizeBy(1);
    private static final Money SECOND_PRIZE = LottoRank.SECOND.multiplePrizeBy(1);
    private static final Money THIRD_PRIZE = LottoRank.THIRD.multiplePrizeBy(1);

    @Test
    @DisplayName("당첨결과 금액 총합 계산 테스트")
    void summarizeLottoPrize() {
        LottoResult lottoResult = new LottoResult(new Money(4000),
                List.of(LottoRank.FIRST, LottoRank.FIRST, LottoRank.SECOND, LottoRank.THIRD));
        Money expected = FIRST_PRIZE.multiply(2).add(SECOND_PRIZE).add(THIRD_PRIZE);
        assertThat(lottoResult.getProfitRate()).isEqualTo(expected.divide(new Money(4000)));
    }

    @Test
    @DisplayName("당첨 결과 순위 갯수 조회 테스트")
    void summarizeCountByLottoRank() {
        LottoResult lottoResult = new LottoResult(new Money(4000),
                List.of(LottoRank.FIRST, LottoRank.FIRST, LottoRank.SECOND, LottoRank.THIRD));

        assertAll("countRank",
                () -> assertThat(lottoResult.getCountByRank(LottoRank.FIRST)).isEqualTo(2),
                () -> assertThat(lottoResult.getCountByRank(LottoRank.SECOND)).isEqualTo(1),
                () -> assertThat(lottoResult.getCountByRank(LottoRank.THIRD)).isEqualTo(1),
                () -> assertThat(lottoResult.getCountByRank(LottoRank.FOURTH)).isEqualTo(0),
                () -> assertThat(lottoResult.getCountByRank(LottoRank.FIFTH)).isEqualTo(0),
                () -> assertThat(lottoResult.getCountByRank(LottoRank.NOTHING)).isEqualTo(0)
        );
    }
}
