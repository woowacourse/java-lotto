package lotto.domain;

import lotto.domain.errors.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PaymentTest {
    @Test
    @DisplayName("로또 가격 정상적으로 입력했을_때")
    void validatePaymentTest_right_number() {
        String validMoney = "1000";
        new Payment(validMoney);
    }

    @Test
    @DisplayName("로또 가격 단위로 입력하지 않았을 때")
    void validatePricePerLottoTest_not_a_lotto_payment_unit() {
        String invalidMoney = "12100";
        assertThatThrownBy(() -> new Payment(invalidMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.CAN_NOT_DIVIDE_BY_PRICE_UNIT
                        .getMessage());
    }

    @ParameterizedTest
    @DisplayName("로또 가격 미만으로 입력했을 때")
    @ValueSource(strings = {"900", "-1", "0"})
    void validateUnderLottoPriceTest_under_lotto_selling_price(String invalidMoney) {
        assertThatThrownBy(() -> new Payment(invalidMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.CAN_NOT_DIVIDE_BY_PRICE_UNIT
                        .getMessage());
    }

    @ParameterizedTest
    @DisplayName("정수로 입력하지 않았을 때")
    @ValueSource(strings = {"천원", "3000.2"})
    void validateNumberTest_not_integer(String invalidMoney) {
        assertThatThrownBy(() -> new Payment(invalidMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NOT_NUMBER
                        .getMessage());
    }
}
