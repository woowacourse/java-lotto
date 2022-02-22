package validator;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PurchaseMoneyValidatorTest {

    @Test
    @DisplayName("구입 금액이 정수인지 확인한다.")
    void checkPurchaseMoneyInteger() {
        String purchaseMoney = "1700dd";
        assertThatThrownBy(() ->
            PurchaseMoneyValidator.validate(purchaseMoney)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("구입 금액이 1,000의 배수인지 확인한다.")
    void checkPurchaseMoneyMultiplesOf1000() {
        String purchaseMoney = "1700";
        assertThatThrownBy(() ->
                PurchaseMoneyValidator.validate(purchaseMoney)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
