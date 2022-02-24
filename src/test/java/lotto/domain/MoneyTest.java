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
}
