package lotto.domain;

import static lotto.common.constant.BusinessRule.*;
import static lotto.common.constant.ErrorMessage.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CashierTest {
    @Test
    @DisplayName("로또의 기준 가격에 비례하여 로또를 발급한다.")
    void generateAmount() {
        Cashier cashier = new Cashier(LOTTO_PRICE);
        assertThat(cashier.getNumberOfLotto()).isEqualTo(1);

        Cashier cashier2 = new Cashier(LOTTO_PRICE * 2);
        assertThat(cashier2.getNumberOfLotto()).isEqualTo(2);
    }

    @Test
    @DisplayName("초기 금액이 기준보다 적을 경우, 예외가 발생한다.")
    void generateAmountErrorWhenLessThenMinimumAmount() {
        var lessThenStandard = LOTTO_PRICE - 100;
        assertThatThrownBy(() -> new Cashier(lessThenStandard))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ERROR_MONEY_LESS_THEN_STANDARD.getMessage());
    }

    @Test
    @DisplayName("초기 금액이 기준에 맞게 나누어 떨어지지 않을 경우, 예외가 발생한다.")
    void generateAmountErrorWhenNotDivisibleByLottoPrice() {
        var NotDivisibleByLottoPrice = LOTTO_PRICE + 100;
        assertThatThrownBy(() -> new Cashier(NotDivisibleByLottoPrice))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ERROR_NOT_DIVIDED_BY_STANDARD.getMessage());
    }
}
