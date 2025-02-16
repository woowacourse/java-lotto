package model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoCalculatorTest {

    @Test
    @DisplayName("개수 및 보너스 당첨 여부에 따라 당첨 횟수를 갱신해야 한다")
    void changeResultCount() {

        LottoCalculator lottoCalculator = new LottoCalculator();

        lottoCalculator.compareWinning(6, false);
        lottoCalculator.compareWinning(5, true);
        lottoCalculator.compareWinning(5, false);
        lottoCalculator.compareWinning(4, false);
        lottoCalculator.compareWinning(3, false);
        lottoCalculator.compareWinning(0, false);

        for (LottoResult result : LottoResult.values()) {
            int expected = lottoCalculator.findTargetResultCount(
                    LottoResult.findTargetResult(result.getPrice(), result.isBonus()));
            Assertions.assertThat(expected)
                    .isEqualTo(1);
        }
    }

    @Test
    @DisplayName("금액에 따른 수익률을 계산한다.")
    void lottoRateOfReturn() {

        int price = 10000;
        double actual = (double) LottoResult.FIRST.getPrice() / price;

        LottoCalculator lottoCalculator = new LottoCalculator();
        lottoCalculator.compareWinning(6, false);
        double expected = lottoCalculator.lottoRateOfReturn(price);

        Assertions.assertThat(expected).isEqualTo(actual);
    }
}