package domain;

import domain.money.IllegalNumberOfManualIssueException;
import domain.money.PurchaseAmount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class PurchaseAmountTest {
    @Test
    void 구입_금액이_1000원_미만인_경우_예외를_던지는지_테스트() {
        assertThrows(IllegalArgumentException.class, () -> PurchaseAmount.valueOf(999));
    }

    @Test
    void 구입_금액이_1000원의_배수가_아닌_경우_예외를_던지는지_테스트() {
        assertThrows(IllegalArgumentException.class, () -> PurchaseAmount.valueOf(1001));
    }

    @Test
    void 수동_구매할_로또_개수가_구입_금액을_초과하면_예외를_던지는지_테스트() {
        PurchaseAmount purchaseAmount = PurchaseAmount.valueOf(5000);
        int numberOfManualIssue = 6;

        assertThrows(IllegalNumberOfManualIssueException.class,
                () -> purchaseAmount.checkNumberOfManualIssue(numberOfManualIssue));
    }
}
