package lotto.domain;

import static lotto.domain.LottoShop.LOTTO_PRIZE;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {

    @Test
    @DisplayName("정상적인 값을 입력헀을 때 정상적으로 생성된다.")
    void test_createMoney() {
        int amount = 1000;
        Money money = new Money(amount,LOTTO_PRIZE);
        assertThat(money).isNotNull();
    }

    @DisplayName("로또를 사기에 money가 부족하다면 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(ints = {-1,0,LOTTO_PRIZE-1})
    void testAmountZero(int amount) {
        assertThatThrownBy(() -> new Money(amount, LOTTO_PRIZE))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("money가 1000원으로 나누어 떨어지지 않을 때 예외가 발생한다.")
    void testAmountNotDivided() {
        assertThatThrownBy(() -> new Money(1500, LOTTO_PRIZE))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
