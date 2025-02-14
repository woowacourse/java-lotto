package lotto.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ProfitTest {

    @Test
    @DisplayName("당첨금이 5_000원, 소비금이 10_000원일 때 수익률은 0.5, 이득여부는 false")
    void profitRate_half() {
        // given
        long winningPrize = 5000L;
        long spentMoney = 10000L;

        // when
        Profit profit = Profit.from(winningPrize, spentMoney);

        // then
        assertThat(profit.rate()).isEqualTo(0.5);
        assertThat(profit.isProfit()).isFalse();
    }

    @DisplayName("수익률이 정상적으로 계산되고, 이득 여부가 올바르게 판단되는지 테스트")
    @ParameterizedTest
    @CsvSource({
            "2000, 1000, 2.0, true",  // 수익 발생
            "1000, 1000, 1.0, true",  // 본전
            "500, 1000, 0.5, false",  // 손실
            "990, 1000, 0.99, false",
            "1010, 1000, 1.01, true"
    })
    void testProfitCalculation(long winningPrize, long spentMoney, double expectedRate, boolean expectedIsProfit) {
        // when
        Profit profit = Profit.from(winningPrize, spentMoney);

        // then
        assertThat(profit.rate()).isEqualTo(expectedRate);
        assertThat(profit.isProfit()).isEqualTo(expectedIsProfit);
    }

    @DisplayName("구입 금액이 0 이하일 경우 예외 발생 테스트")
    @ParameterizedTest
    @CsvSource({
            "1000, 0",
            "1000, -1",
            "0, 0",
            "0, -1000"
    })
    void testInvalidSpentMoneyThrowsException(long winningPrize, long spentMoney) {
        // then
        assertThatThrownBy(() -> Profit.from(winningPrize, spentMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구입 금액이 0 이하일 수 없습니다.");
    }

}
