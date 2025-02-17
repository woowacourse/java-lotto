package domain;

import static domain.LottoRank.BOOM;
import static domain.LottoRank.FIFTH_PLACE;
import static domain.LottoRank.FIRST_PLACE;
import static domain.LottoRank.FOURTH_PLACE;
import static domain.LottoRank.SECOND_PLACE;
import static domain.LottoRank.THIRD_PLACE;

import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoStatisticsTest {

    @Test
    @DisplayName("수익률 계산 테스트")
    public void success_1() {
        int input = 10000;
        Money money = new Money(input);
        Map<LottoRank, Integer> result = Map.of(
            BOOM, 7,
            FIFTH_PLACE, 1,
            FOURTH_PLACE, 2,
            THIRD_PLACE, 0,
            SECOND_PLACE, 0,
            FIRST_PLACE, 0
        );

        LottoStatistics lottoStatistics = new LottoStatistics(result);
        int profit = lottoStatistics.calculateProfit();
        double profitRate = money.calculateProfitRate(profit);
        Assertions.assertThat(profitRate).isEqualTo(10.50);
    }
}
