package domain.bettingMoney;

import domain.lotto.TicketCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

class BettingMoneyTest {

    @DisplayName("BettingMoney 정상 생성 테스트.")
    @ParameterizedTest
    @ValueSource(ints = {10000, 14000})
    void bettingMoneyGenerateTest(int value) {
        assertThatCode(() -> new BettingMoney(value))
                .doesNotThrowAnyException();
    }

    @DisplayName("보장된 숫자(양수의 숫자)가 아닐때 에러가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    void bettingMoneyNotGuaranteedErrorTest(int value) {
        assertThatThrownBy(() -> new BettingMoney(value))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("티켓 반환 테스트.")
    @ParameterizedTest
    @ValueSource(ints = {1000, 1100, 2200})
    void bettingMoneyTicketCountTest(int value) {
        //given
        int ticketPrice = 1000;

        //when
        BettingMoney bettingMoney = new BettingMoney(value);
        TicketCount ticketCount = bettingMoney.getTicketCount(ticketPrice);

        //then
        assertThat(ticketCount.getTicketCount()).isEqualTo(value / ticketPrice);
    }

    @DisplayName("티켓값보다 낮은 배팅 금액이 들어오면 에러가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {100, 200, 999})
    void bettingMoneyTicketCountErrorTest(int value) {
        //given
        int ticketPrice = 1000;

        //when
        BettingMoney bettingMoney = new BettingMoney(value);

        //then
        assertThatThrownBy(() -> bettingMoney.getTicketCount(ticketPrice))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("소수점 자리가 0일 때, 수익률 반환 값 테스트.")
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

    @DisplayName("수익률 반환 테스트.")
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

    @DisplayName("int 최대값 이상의 상금 정상 계산 테스트다.")
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