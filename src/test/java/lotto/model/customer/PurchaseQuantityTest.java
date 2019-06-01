package lotto.model.customer;

import lotto.model.customer.exception.InvalidPurchaseQuantityException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PurchaseQuantityTest {

    @Test
    void 구입_개수가_음수일_때_예외_발생() {
        assertThrows(InvalidPurchaseQuantityException.class, () -> PurchaseQuantity.from(-1));
    }
}
