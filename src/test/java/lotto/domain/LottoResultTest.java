package lotto.domain;

import static lotto.domain.enumeration.Rank.FIFTH;
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
        Map<Rank, Integer> ranks = Map.of(FIFTH, 1);

        LottoResult lottoResult = new LottoResult(ranks);

        LottoPurchaseMoney lottoPurchaseMoney = LottoPurchaseMoney.create(14000);

        // when
        double result = lottoResult.calculateYield(lottoPurchaseMoney);

        // then
        assertThat(result).isEqualTo((double) FIFTH.getPrizeMoney() / lottoPurchaseMoney.getPrice());
    }

    @DisplayName("같은 등수가 여러 개 당첨된 경우 올바른 수익률을 계산하여 반환한다.")
    @Test
    void checkSamePlace() {
        // given
        Map<Rank, Integer> ranks = Map.of(FIFTH, 2);

        LottoResult lottoResult = new LottoResult(ranks);

        LottoPurchaseMoney lottoPurchaseMoney = LottoPurchaseMoney.create(14000);

        // when
        double result = lottoResult.calculateYield(lottoPurchaseMoney);

        // then
        assertThat(result).isEqualTo((double) (FIFTH.getPrizeMoney() * 2) / lottoPurchaseMoney.getPrice());
    }
}