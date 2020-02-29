package lotto.domain;

import lotto.domain.customer.Money;
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
        assertThatThrownBy(() -> new Money(bettingMoney, 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("%d는 최소 구매 금액보다 작습니다.", bettingMoney);
    }

    @DisplayName("예외 테스트: 입력한 금액을 초과하는 수동 로또 구매 희망 수 입력시 Exception 발생")
    @Test
    void test2() {
        assertThatThrownBy(() -> new Money(1000, 2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(String.format("구매 가능한 로또 수: %d, 구매 로또 수: %d - 잔액이 부족합니다.", 1, 2));
    }

    @DisplayName("자동 로또 구매 갯수 확인")
    @Test
    void test3() {
        Money money = new Money(10000, 2);
        assertThat(money.getNumberOfLeftTickets()).isEqualTo(8);
    }

    @DisplayName("현재 금액으로 살 수 있는 티켓 수 확인")
    @ParameterizedTest
    @CsvSource(value = {"1000,1", "1500,1", "2000,2"})
    void test4(int bettingMoney, int expectedNumberOfTickets) {
        Money money = new Money(bettingMoney, 1);

        //then
        assertThat(money.getNumberOfManualTickets() + money.getNumberOfLeftTickets()).isEqualTo(expectedNumberOfTickets);
    }
}
