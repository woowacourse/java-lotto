package lotto.model;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class YieldTest {

    @ParameterizedTest
    @CsvSource(value = {"14000:true", "13999:false"}, delimiter = ':')
    @DisplayName("수익률 1기준 이득 테스트")
    void isGainTest(Long totalWinningMoney, boolean expected) {
        LottoMoney lottoMoney = new LottoMoney(14000, 0);
        Yield yield = new Yield(lottoMoney, totalWinningMoney);

        assertThat(yield.isGain()).isEqualTo(expected);
    }

    @Test
    @DisplayName("수익률 계산 테스트")
    void calculateTest() {
        LottoMoney lottoMoney = new LottoMoney(14000, 0);
        long totalWinningMoney = 5000L;
        Yield yield = new Yield(lottoMoney, totalWinningMoney);

        assertThat(yield.getYield()).isCloseTo(0.35714f, Percentage.withPercentage(0.01));
    }
}
