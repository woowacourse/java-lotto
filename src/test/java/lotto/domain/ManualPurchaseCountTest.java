package lotto.domain;

import lotto.domain.errors.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ManualPurchaseCountTest {
    @Test
    @DisplayName("숫자가 음수일_때")
    void validatePositiveNumberTest_negative_number() {
        Payment payment = new Payment("1000");
        String negativePurchaseCount = "-1";

        assertThatThrownBy(() -> new ManualPurchaseCount(negativePurchaseCount, payment))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NOT_POSITIVE
                        .getMessage());
    }

    @Test
    @DisplayName("수동 구매량이 총 구매 매수보다 클 때")
    void validateLessThanLottoCountTest_manual_purchase_over_payment() {
        Payment payment = new Payment("10000");
        String invalidPurchaseCount = "11";

        assertThatThrownBy(() -> new ManualPurchaseCount(invalidPurchaseCount, payment))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.MANUAL_COUNT_OVER_TOTAL
                        .getMessage());
    }

    @Test
    @DisplayName("올바른 구매 수량 일 때")
    void validateLessThanLottoCountTest_right_purchase() {
        Payment payment = new Payment("12000");
        String validPurchaseCount = "12";

        new ManualPurchaseCount(validPurchaseCount, payment);
    }
}
