package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AmountPaidTest {

    @DisplayName("구매 금액은 1000으로 나누어 떨어지지 않으면 예외를 발생한다.")
    @Test
    void 구매_금액은_1000으로_나누어_떨어지지_않으면_예외를_발생한다() {

        assertThatThrownBy(() -> new AmountPaid(15500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 금액은 1000으로 나누어 떨어져야 합니다.");
    }

    @DisplayName("구매 금액은 음수면 예외를 발생한다.")
    @Test
    void 구매_금액은_음수면_예외를_발생한다() {

        assertThatThrownBy(() -> new AmountPaid(-15000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 음수는 입력할 수 없습니다.");
    }
}
