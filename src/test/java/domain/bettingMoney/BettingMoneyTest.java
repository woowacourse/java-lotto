package domain.bettingMoney;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

class BettingMoneyTest {

    @DisplayName("BettingMoney 정상 생성테스트.")
    @ParameterizedTest
    @ValueSource(ints = {10000, 14000})
    void bettingMoneyGenerateTest(int value) {
        assertThatCode(() -> new BettingMoney(value))
                .doesNotThrowAnyException();
    }

    @DisplayName("보장된 숫자(로또 티켓 구입 금액보다 큰 양수의 숫자))가 아니면 에러가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 900})
    void bettingMoneyNotGuaranteedErrorTest(int value) {
        assertThatThrownBy(() -> new BettingMoney(value))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("수익률 반환 값 테스트")
    @Test
    void earningsRateValueTest() {
        //given
        int money = 2000;
        long totalPrize = 10000;

        //when
        BettingMoney bettingMoney = new BettingMoney(money);
        BigDecimal earningRate = bettingMoney.getEarningRate(totalPrize);

        //then
        assertThat(earningRate).isEqualTo(new BigDecimal("5.00"));
    }

    @DisplayName("수익률 반환 테스트")
    @Test
    void earningsRateTest() {
        //given
        int money = 3000;
        long totalPrize = 10000;

        //when
        BettingMoney bettingMoney = new BettingMoney(money);
        BigDecimal earningRate = bettingMoney.getEarningRate(totalPrize);

        //then
        assertThat(earningRate).isEqualTo(BigDecimal.valueOf(3.33));
    }

    @DisplayName("int 최대값 이상의 상금도 정상 계산된다 테스트")
    @Test
    void earningsRateMaXIntegerTest() {
        //given
        int money = 3000;
        long totalPrize = 200_000_000_000L;

        //when
        BettingMoney bettingMoney = new BettingMoney(money);
        BigDecimal earningRate = bettingMoney.getEarningRate(totalPrize);

        //then
        assertThat(earningRate.intValue()).isEqualTo(totalPrize / money);
    }
}