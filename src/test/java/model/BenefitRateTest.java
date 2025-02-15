package model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BenefitRateTest {

    @Test
    @DisplayName("수익률 계산 테스트")
    void benefitRate() {
        // given
        LottoPurchase lottoPurchase = LottoPurchase.of(10000);
        int benefit = 5000;

        double expected = 0.5;

        // when
        BenefitRate benefitRate = new BenefitRate(lottoPurchase, benefit);

        // then
        Assertions.assertThat(benefitRate.getNumber()).isEqualTo(expected);
    }
}
