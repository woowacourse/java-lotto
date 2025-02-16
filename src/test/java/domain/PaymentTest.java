package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PaymentTest {
    @DisplayName("올바른 구매금액 입력에 대해서 예외가 발생하지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {1000, 2000, 14000})
    void 올바른_구매금액_테스트(int money) {
        Assertions.assertThatCode(() -> new Payment(money))
                .doesNotThrowAnyException();
    }

    @DisplayName("구매금액이 양수가 아닌 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, -1, -100, -1000})
    void 구매금액이_양수가_아닌_경우_테스트(int money) {
        Assertions.assertThatThrownBy(() -> new Payment(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구매금액이 양수가 아닙니다.");
    }

    @DisplayName("구매금액의 단위가 올바르지 않은 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 10, 100, 500})
    void 구매금액의_단위가_올바르지_않은_경우_테스트(int money) {
        Assertions.assertThatThrownBy(() -> new Payment(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바르지 않은 구매금액 단위입니다.");
    }
}