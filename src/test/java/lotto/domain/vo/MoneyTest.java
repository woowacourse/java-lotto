package lotto.domain.vo;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

    @DisplayName("입력한 숫자로 Money 객체를 생성한다")
    @Test
    void money_constructor_test() {
        assertThatNoException()
                .isThrownBy(() -> new Money(10000));
    }

    @DisplayName("금액과 가격이 주어질 때 몇개 구매할 수 있는지 반환한다.")
    @Test
    void divide_test() {
        Money money = new Money(10000);
        Money price = new Money(3000);

        int count = money.divide(price);

        assertThat(count).isEqualTo(3);
    }

    @DisplayName("금액과 사용 금액이 주어질 때 남은 금액을 계산한다.")
    @Test
    void subtract_test() {
        Money money = new Money(10000);
        Money price = new Money(3000);

        Money changes = money.subtract(price);

        assertThat(changes.get()).isEqualTo(7000);
    }

    @DisplayName("음수를 입력했을 때 예외가 발생한다")
    @Test
    void money_constructor_error_on_negative_test() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Money(-1000))
                .withMessage("금액은 음수를 입력하면 안됩니다.");
    }
}
