package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class ProfitTest {

    Map<Rank, Integer> calculateResult = new LinkedHashMap<>();

    @BeforeEach
    void init() {
        for (Rank rank : Rank.values()) {
            calculateResult.put(rank, 0);
        }
    }

    @Nested
    @DisplayName("당첨결과와 구매 금액이 들어왔을 경우 수익률을 계산한다")
    class profit {

        @DisplayName("구매 금액이 1000, 1등이 당첨되었을 경우, 2000000.0의 수익률이 결과로 나와야 한다")
        @Test
        void profit_test_1() {
            assertProfit(1000, Rank.FIRST, 2000000.0);
        }

        @DisplayName("구매 금액이 5000, 5등이 당첨되었을 경우, 1.0의 수익률이 결과로 나와야 한다")
        @Test
        void profit_test_2() {
            assertProfit(5000, Rank.FIFTH, 1.0);
        }

        @DisplayName("구매 금액이 1000, 미당첨일 경우, 수익률은 0.0이어야 한다.")
        @Test
        void profit_test_3() {
            assertProfit(1000, Rank.NONE, 0.0);
        }
    }

    private void assertProfit(int purchaseAmount, Rank expectedRank, double expectedProfit) {
        calculateResult.put(expectedRank, 1);
        Profit profit = Profit.of(calculateResult, purchaseAmount);
        assertThat(profit.getResult()).isEqualTo(expectedProfit);
    }

}
