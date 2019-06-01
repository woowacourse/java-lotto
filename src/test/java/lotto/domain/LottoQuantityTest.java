package lotto.domain;

import lotto.domain.purchaseamount.PurchaseAmount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoQuantityTest {
    @Test
    void 숫자_아닌_값() {
        assertThrows(InvalidLottoQuantityException.class, () -> {
            LottoQuantity.create("a");
        });
    }

    @Test
    void 음수_값() {
        assertThrows(InvalidLottoQuantityException.class, () -> {
            LottoQuantity.create(-3);
        });
    }

    @Test
    void 유효개수_불가_확인() {
        PurchaseAmount purchaseAmount = PurchaseAmount.create("1000");
        assertThrows(InvalidLottoQuantityException.class, () -> {
            LottoQuantity.create(3).validateAvailable(purchaseAmount);
        });
    }

    @Test
    void 유효개수_가능_확인() {
        PurchaseAmount purchaseAmount = PurchaseAmount.create("1000");
        assertDoesNotThrow(() -> {
            LottoQuantity.create(1).validateAvailable(purchaseAmount);
        });
    }
}