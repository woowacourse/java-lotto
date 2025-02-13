package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.dto.Profit;

class CalculatorTest {
    @Test
    @DisplayName("로또 번호가 6개가 아니라면 예외를 발생시킨다.")
    void testLottoNumber_sizeException() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5)))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("수익률 계산 테스트")
    void testCalculateProfit() {
        HashMap<MatchStatistics, Integer> map = new HashMap<>();
        Amount amount = new Amount(10000);

        map.put(MatchStatistics.MATCH_THREE, 3);

        Calculator calculator = new Calculator();
        Profit profit = calculator.calculate(map, amount);

        assertThat(profit.rate()).isEqualTo(1.5);
    }
}