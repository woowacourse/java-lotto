package model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoResultTest {
    private static final Money FIRST_PRIZE = LottoRank.FIRST.getPrize();
    private static final Money SECOND_PRIZE = LottoRank.SECOND.getPrize();
    private static final Money THIRD_PRIZE = LottoRank.THIRD.getPrize();

    @Test
    @DisplayName("당첨결과 금액 총합 계산 테스트")
    void summarizeLottoPrize() {
        LottoResult lottoResult = new LottoResult(new Money(4000));
        lottoResult.add(LottoRank.FIRST);
        lottoResult.add(LottoRank.FIRST);
        lottoResult.add(LottoRank.SECOND);
        lottoResult.add(LottoRank.THIRD);
        Money expected = FIRST_PRIZE.multiply(2).add(SECOND_PRIZE).add(THIRD_PRIZE);
        assertThat(lottoResult.getTotalPrize()).isEqualTo(expected);
    }

    @Test
    @DisplayName("당첨 결과 순위 갯수 조회 테스트")
    void summarizeCountByLottoRank() {
        LottoResult lottoResult = new LottoResult(new Money(4000));
        lottoResult.add(LottoRank.FIRST);
        lottoResult.add(LottoRank.FIRST);
        lottoResult.add(LottoRank.SECOND);
        lottoResult.add(LottoRank.THIRD);

        assertThat(lottoResult.getCountByRank(LottoRank.FIRST)).isEqualTo(2);
        assertThat(lottoResult.getCountByRank(LottoRank.SECOND)).isEqualTo(1);
        assertThat(lottoResult.getCountByRank(LottoRank.THIRD)).isEqualTo(1);
        assertThat(lottoResult.getCountByRank(LottoRank.FOURTH)).isEqualTo(0);
        assertThat(lottoResult.getCountByRank(LottoRank.FIFTH)).isEqualTo(0);
        assertThat(lottoResult.getCountByRank(LottoRank.NOTHING)).isEqualTo(0);
    }
}
