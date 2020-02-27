package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoneyTest {

    @DisplayName("예외 테스트: 로또 한 장 가격보다 작은 값 입력시 Exception 발생")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 999})
    void test1(int bettingMoney) {
        assertThatThrownBy(() -> new Money(bettingMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("%d는 최소 구매 금액보다 작습니다.", bettingMoney);
    }

    @DisplayName("예외 테스트: 입력한 금액을 초과하는 수동 로또 구매 희망 수 입력시 Exception 발생")
    @Test
    void test2() {
        //given
        Money money = new Money(1000);

        //when, then
        assertThatThrownBy(() -> money.spendOnManualLotto(2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(String.format("투입 금액: %d, 구매 로또 수: %d - 잔액이 부족합니다.", 1000, 2));
    }

    @DisplayName("수동 로또 구매 후 남은 금액 확인")
    @Test
    void test3() {
        //given
        Money money = new Money(10000);
        Money expectedMoney = new Money(8000);

        //when
        money.spendOnManualLotto(2);

        //then
        assertThat(money).isEqualTo(expectedMoney);
    }

    @DisplayName("현재 금액으로 살 수 있는 티켓 수 확인")
    @ParameterizedTest
    @CsvSource(value = {"1000,1", "1500,1", "2000,2"})
    void test4(int bettingMoney, int expectedNumberOfTickets) {
        //given
        Money money = new Money(bettingMoney);

        //when
        int numberOfAffordableTickets = money.calculateAffordableTicketNumbers();

        //then
        assertThat(numberOfAffordableTickets).isEqualTo(expectedNumberOfTickets);
    }

    @DisplayName("자동으로 티켓 구매 후 남은 금액 확인")
    @Test
    void test5() {
        //given
        Money money = new Money(10500);
        Money expectedMoney = new Money(8500);

        //when
        money.spendOnAutoLotto(2);

        //then
        assertThat(money).isEqualTo(expectedMoney);
    }
}
