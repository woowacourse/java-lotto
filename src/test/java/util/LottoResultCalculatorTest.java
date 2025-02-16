package util;

import static org.assertj.core.api.Assertions.assertThat;

import constant.WinningCount;
import java.util.EnumMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoResultCalculatorTest {
    @Test
    @DisplayName("당첨 되었을 때 수익률 계산 검증")
    void earningRateTest() {
        // given
        Map<WinningCount, Integer> result = new EnumMap<>(WinningCount.class);
        result.put(WinningCount.THREE, 2);
        result.put(WinningCount.FOUR, 1);
        result.put(WinningCount.FIVE, 1);
        result.put(WinningCount.FIVE_BONUS, 1);
        int purchasedCost = 430000;
        // when
        Double earningRate = LottoResultCalculator.calculateEarningRate(result, purchasedCost);
        // then
        assertThat(earningRate).isEqualTo(73.4);
    }

    @DisplayName("당첨 되지 않았을 때 수익률 계산 검증")
    @Test
    void earningRateTest2() {
        // given
        Map<WinningCount, Integer> result = new EnumMap<>(WinningCount.class);
        result.put(WinningCount.NONE, 43);
        int purchasedCost = 430000;
        // when
        Double earningRate = LottoResultCalculator.calculateEarningRate(result, purchasedCost);
        // then
        assertThat(earningRate).isEqualTo(0);
    }

}