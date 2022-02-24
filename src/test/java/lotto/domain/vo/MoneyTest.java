package lotto.domain.vo;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

    @DisplayName("Money 생성자 테스트")
    @Test
    void money_constructor_test() {
        assertThatNoException()
                .isThrownBy(() -> new Money(10000));
    }

    @DisplayName("Money 생성자 음수 입력 예외 테스트")
    @Test
    void money_constructor_error_on_negative_test() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Money(-1000))
                .withMessage("금액은 음수를 입력하면 안됩니다.");
    }
}
