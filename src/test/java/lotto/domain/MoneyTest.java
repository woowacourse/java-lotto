package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @DisplayName("Money 생성자는 음수가 아닌 정수를 입력받아 amount 값을 초기화한다.")
    @Test
    void constructor() {
        assertThatNoException().isThrownBy(() -> new Money(10000));
    }

    @DisplayName("Money 생성자는 음수를 입력받으면 예외가 발생한다.")
    @Test
    void constructor_ErrorOnNegative() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Money(-1000))
                .withMessage("금액은 음수를 입력하면 안됩니다.");
    }

    @DisplayName("canBuyNumber 메서드는 구매하려는 item의 Money를 입력받아 구매 가능한 개수를 반환한다.")
    @Test
    void canBuyNumber() {
        // given
        Money itemMoney = new Money(1000);
        Money myMoney = new Money(10000);
        // then
        assertThat(myMoney.canBuyNumber(itemMoney)).isEqualTo(10);
    }

    @DisplayName("minus 메서드는 money를 입력받아 원본 money의 amount에 뺀다.")
    @Test
    void minus() {
        // given
        Money money = new Money(10000);
        money.minus(new Money(3000));
        // then
        assertThat(money).isEqualTo(new Money(7000));
    }

    @DisplayName("minus 메서드는 들어온 money가 더 클 경우 예외를 발생한다.")
    @Test
    void minus_errorOnOverMoney() {
        // given
        Money money = new Money(10000);
        Money itemMoney = new Money(20000);
        // then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> money.minus(itemMoney))
                .withMessage("가지고 있는 돈을 초과해서 지불할 수 없습니다.");
    }
}
