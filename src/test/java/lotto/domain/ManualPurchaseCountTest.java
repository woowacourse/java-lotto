package lotto.domain;

import lotto.domain.errors.ErrorMessage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ManualPurchaseCountTest {
    @Test
    void validatePositiveNumberTest_숫자가_음수일_때() {
        Payment payment = new Payment("1000");
        String negativePurchaseCount = "-1";

        assertThatThrownBy(() -> new ManualPurchaseCount(negativePurchaseCount, payment))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NOT_POSITIVE
                        .getMessage());
    }

    @Test
    void validateLessThanLottoCountTest_수동_구매량이_총_구매_매수보다_클_때() {
        Payment payment = new Payment("10000");
        String invalidPurchaseCount = "11";

        assertThatThrownBy(() -> new ManualPurchaseCount(invalidPurchaseCount, payment))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.MANUAL_COUNT_OVER_TOTAL
                        .getMessage());
    }

    @Test
    void validateLessThanLottoCountTest_올바른_구매_수량_일_때() {
        Payment payment = new Payment("12000");
        String validPurchaseCount = "12";

        new ManualPurchaseCount(validPurchaseCount, payment);
    }
}
