package lotto.domain;

import lotto.domain.errors.ErrorMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PaymentTest {
    @Test
    void validatePaymentTest_로또_가격_정상적으로_입력했을_때() {
        String validMoney = "1000";
        new Payment(validMoney);
    }

    @Test
    void validatePricePerLottoTest_로또_가격_단위로_입력하지_않았을_때() {
        String invalidMoney = "12100";
        assertThatThrownBy(() -> new Payment(invalidMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.CAN_NOT_DIVIDE_BY_PRICE_UNIT
                        .getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"900", "-1", "0"})
    void validateUnderLottoPriceTest_로또_가격_미만으로_입력했을_때(String invalidMoney) {
        assertThatThrownBy(() -> new Payment(invalidMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.CAN_NOT_DIVIDE_BY_PRICE_UNIT
                        .getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"천원", "3000.2"})
    void validateNumberTest_정수로_입력하지_않았을_때(String invalidMoney) {
        assertThatThrownBy(() -> new Payment(invalidMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NOT_NUMBER
                        .getMessage());
    }
}
