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

    @DisplayName("음수를 입력했을 때 예외가 발생한다")
    @Test
    void money_constructor_error_on_negative_test() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Money(-1000))
                .withMessage("금액은 음수를 입력하면 안됩니다.");
    }
}
