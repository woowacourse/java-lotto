package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProfitRateTest {

    @Test
    @DisplayName("수익률을 double형 실수로 반환")
    void getDoubleValue() {
        ProfitRate rate = new ProfitRate(new BigDecimal(10.24));
        assertThat(rate.getDoubleValue()).isCloseTo(10.24, Offset.offset(0.001));
    }

    @Test
    @DisplayName("수익률이 이익 구간인지 확인")
    void isProfit() {
        ProfitRate rate = new ProfitRate(new BigDecimal(2));
        assertThat(rate.isProfit()).isTrue();
        assertThat(rate.isLoss()).isFalse();
        assertThat(rate.isPrincipal()).isFalse();
    }

    @Test
    @DisplayName("수익률이 손해 구간인지 확인")
    void isLoss() {
        ProfitRate rate = new ProfitRate(new BigDecimal(0.99));
        assertThat(rate.isLoss()).isTrue();
        assertThat(rate.isProfit()).isFalse();
        assertThat(rate.isPrincipal()).isFalse();
    }

    @Test
    @DisplayName("수익률이 본전 구간인지 확인")
    void isPrincipal() {
        ProfitRate rate = new ProfitRate(new BigDecimal(1));
        assertThat(rate.isPrincipal()).isTrue();
        assertThat(rate.isProfit()).isFalse();
        assertThat(rate.isLoss()).isFalse();
    }

}
