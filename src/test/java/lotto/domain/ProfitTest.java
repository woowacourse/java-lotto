package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProfitTest {
    @DisplayName("구입금액과 당첨결과들을 통해서 수익률을 계산")
    @Test
    void getProfitWithoutDecimalPoint() {
        Money purchaseMoney = Money.createPurchaseMoney(14000);
        List<Rank> ranks = Arrays.asList(Rank.FIFTH, Rank.FOURTH);
        Profit profit = new Profit(purchaseMoney, ranks);

        int actual = profit.getProfitWithoutDecimalPoint();
        int expected = 392;

        assertThat(actual).isEqualTo(expected);
    }
}
