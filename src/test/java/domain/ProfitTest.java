package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedHashMap;
import java.util.List;
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
    @DisplayName("단일 당첨 케이스")
    class singleProfit {

        @DisplayName("구매 금액이 1000, 1등이 당첨되었을 경우, 2000000.0의 수익률이 결과로 나와야 한다")
        @Test
        void profit_test_1() {
            assertValidateProfit(1000, List.of(Rank.FIRST), 2000000.0);
        }

        @DisplayName("구매 금액이 5000, 5등이 당첨되었을 경우, 1.0의 수익률이 결과로 나와야 한다")
        @Test
        void profit_test_2() {
            assertValidateProfit(5000, List.of(Rank.FIFTH), 1.0);
        }

        @DisplayName("구매 금액이 1000, 미당첨일 경우, 수익률은 0.0이어야 한다.")
        @Test
        void profit_test_3() {
            assertValidateProfit(1000, List.of(Rank.NONE), 0.0);
        }
    }

    @Nested
    @DisplayName("중복 당첨 케이스")
    class profits {

        @DisplayName("구매 금액이 5000, 1등, 2등에 당첨되었을 경우, 406000.0의 수익률이 결과로 나와야 한다")
        @Test
        void profits_test_1() {
            assertValidateProfit(5000,
                List.of(Rank.FIRST, Rank.SECOND),
                406000.0);
        }

        @DisplayName("구매 금액이 5000, 5등에 2번 당첨되었을 경우, 의 수익률이 결과로 나와야 한다")
        @Test
        void profits_test_2() {
            assertValidateProfit(5000,
                List.of(Rank.FIFTH, Rank.FIFTH),
                2.0);
        }

    }

    private void assertValidateProfit(int purchaseAmount, List<Rank> expectedRank,
        double expectedProfit) {

        for (Rank rank : expectedRank) {
            calculateResult.put(rank, calculateResult.get(rank) + 1);
        }
        Profit profit = Profit.of(calculateResult, purchaseAmount);
        assertThat(profit.getResult()).isEqualTo(expectedProfit);
    }

}
