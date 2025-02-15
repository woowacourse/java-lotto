package lotto.domain;

import static lotto.common.constant.ErrorMessage.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AmountTest {
    @Test
    @DisplayName("Amount는 생성될 때 ")
    void generateAmount() {
        Amount amount = new Amount(1000);
        assertThat(amount.getAmount()).isEqualTo(1);
    }

    @Test
    @DisplayName("초기 금액이 기준보다 적을 경우, 예외가 발생한다.")
    void generateAmountErrorWhenLessThenMinimumAmount() {
        assertThatThrownBy(() -> new Amount(100))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ERROR_MONEY_LESS_THEN_STANDARD.getMessage());
    }

    @Test
    @DisplayName("초기 금액이 기준에 맞게 나누어 떨어지지 않을 경우, 예외가 발생한다.")
    void generateAmountErrorWhenNotDivisibleByLottoPrice() {
        assertThatThrownBy(() -> new Amount(1500))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ERROR_NOT_DIVIDED_BY_STANDARD.getMessage());
    }
}
