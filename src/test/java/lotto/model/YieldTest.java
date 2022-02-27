package lotto.model;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class YieldTest {

    @ParameterizedTest
    @CsvSource(value = {"1.00f,true", "0.99f, false"}, delimiter = ',')
    @DisplayName("수익률 1기준 이득여부 테스트")
    void isGainTest(float value, boolean expected) {
        Yield yield = new Yield(value);

        assertThat(yield.isGain()).isEqualTo(expected);
    }

    @Test
    @DisplayName("수익률 계산 테스트")
    void calculateTest() {
        LottoMoney lottoMoney = new LottoMoney(14000);
        Long totalWinningMoney = 5000L;
        Yield yield = Yield.calculate(lottoMoney, totalWinningMoney);

        assertThat(yield.getYield()).isCloseTo(0.35714f, Percentage.withPercentage(0.01));
    }
}
