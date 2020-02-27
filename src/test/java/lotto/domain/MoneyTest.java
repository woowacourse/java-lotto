package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
    void name() {
        //given
        Money money = new Money(1000);

        //when, then
        assertThatThrownBy(() -> money.buyManualLotto(2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(String.format("투입 금액: %d, 구매 로또 수: %d - 잔액이 부족합니다.", 1000, 2));
    }
}
