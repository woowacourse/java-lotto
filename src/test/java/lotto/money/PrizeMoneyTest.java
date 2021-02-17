package lotto.money;

import lotto.ranking.Ranking;
import lotto.ranking.Statistics;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PrizeMoneyTest {

    @Test
    @DisplayName("당첨 금액 생성 확인")
    void prizeMoneyCreate() {
        Statistics statistics = new Statistics(Arrays.asList(Ranking.FIRST, Ranking.FORTH, Ranking.FORTH, Ranking.FORTH));
        PrizeMoney prizeMoney = new PrizeMoney();
        assertThat(prizeMoney.totalPrize(statistics)).isEqualTo(2_000_150_000);
    }
}
