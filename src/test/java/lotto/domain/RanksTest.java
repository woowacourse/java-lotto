package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RanksTest {
    @DisplayName("구입금액과 당첨결과들을 통해서 수익률을 계산")
    @Test
    void calculateProfit() {
        Money purchaseMoney = Money.ofPurchaseMoney(14000);
        List<Rank> ranksValues = Arrays.asList(Rank.FIFTH, Rank.FOURTH);
        Ranks ranks = Ranks.of(ranksValues);


        Profit actual = ranks.calculateProfit(purchaseMoney);
        Profit expected = Profit.of((double)55000/14000*100);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void frequency() {
    }
}