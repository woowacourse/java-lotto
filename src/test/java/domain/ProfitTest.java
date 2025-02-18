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
    class SingleProfit {

        @DisplayName("구매 금액이 1000, 1등이 당첨되었을 경우, 2000000.0의 수익률이 결과로 나와야 한다")
        @Test
        void purchase_amount_1000_and_1st_case() {
            assertValidateProfit(1000, List.of(Rank.FIRST), 2000000.0);
        }

        @DisplayName("구매 금액이 5000, 5등이 당첨되었을 경우, 1.0의 수익률이 결과로 나와야 한다")
        @Test
        void purchase_amount_5000_and_5st_case() {
            assertValidateProfit(5000, List.of(Rank.FIFTH), 1.0);
        }

        @DisplayName("구매 금액이 1000, 미당첨일 경우, 수익률은 0.0이어야 한다.")
        @Test
        void purchase_amount_1000_and_none_case() {
            assertValidateProfit(1000, List.of(Rank.NONE), 0.0);
        }
    }

    @Nested
    @DisplayName("중복 당첨 케이스")
    class Profits {

        @DisplayName("구매 금액이 5000, 1등, 2등에 당첨되었을 경우, 406000.0의 수익률이 결과로 나와야 한다")
        @Test
        void purchase_amount_5000_and_1st_and_2st_case() {
            assertValidateProfit(5000,
                List.of(Rank.FIRST, Rank.SECOND),
                406000.0);
        }

        @DisplayName("구매 금액이 5000, 5등에 2번 당첨되었을 경우, 2.0의 수익률이 결과로 나와야 한다")
        @Test
        void purchase_amount_5000_and_5st_two_case() {
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
