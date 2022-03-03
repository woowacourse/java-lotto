package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoResultTest {

    @DisplayName("구입 금액이 14000원이고 5등에 1회 당첨되면 총 수익률은 0.35 이상이다.")
    @Test
    void 수익률_계산_5등_1회() {
        // given
        Map<Rank, Long> ranks = Map.of(Rank.FIFTH, 1L);
        LottoResult lottoResult = new LottoResult(ranks);
        Money money = new Money(14000);

        // when
        double result = lottoResult.calculateYield(money);

        // then
        assertThat(result).isGreaterThan(0.35);
    }

    @DisplayName("구입 금액이 14000원이고 5등에 1회 당첨되면 총 수익률은 0.71 이상이다.")
    @Test
    void 수익률_계산_5등_2회() {
        // given
        Map<Rank, Long> ranks = Map.of(Rank.FIFTH, 2L);
        LottoResult lottoResult = new LottoResult(ranks);
        Money money = new Money(14000);

        // when
        double result = lottoResult.calculateYield(money);

        // then
        assertThat(result).isGreaterThan(0.71);
    }
}