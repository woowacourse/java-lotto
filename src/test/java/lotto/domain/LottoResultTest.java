package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import lotto.domain.enumeration.Rank;
import lotto.domain.vo.LottoPurchaseMoney;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoResultTest {

    @DisplayName("구입 금액이 주어지면 수익률을 계산하여 반환한다.")
    @Test
    void checkYield() {
        // given
        Map<Rank, Integer> ranks = Map.of(Rank.FIFTH, 1);

        LottoResult lottoResult = new LottoResult(ranks);

        LottoPurchaseMoney lottoPurchaseMoney = LottoPurchaseMoney.create(14000);

        // when
        double result = lottoResult.calculateYield(lottoPurchaseMoney);

        // then
        assertThat(Math.floor(result * 100) / 100).isEqualTo(0.35);
    }
}