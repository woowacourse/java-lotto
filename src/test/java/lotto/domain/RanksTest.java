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
        Money purchaseMoney = Money.createPurchaseMoney(14000);
        List<Rank> ranksValues = Arrays.asList(Rank.FIFTH, Rank.FOURTH);
        Ranks ranks = Ranks.valueOf(ranksValues);


        double actual = ranks.calculateProfit(purchaseMoney);
        double expected = (double) 55000 / 14000 * 100;

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("Ranks안에 해당하는 Rank의 개수를 반환")
    @Test
    void frequency() {
        List<Rank> ranksValues = Arrays.asList(Rank.FIFTH, Rank.FOURTH, Rank.FIFTH);
        Ranks ranks = Ranks.valueOf(ranksValues);

        Rank findingRank = Rank.FIFTH;

        assertThat(ranks.frequency(findingRank)).isEqualTo(2);
    }
}