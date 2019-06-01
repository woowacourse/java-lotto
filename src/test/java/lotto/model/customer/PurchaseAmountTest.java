package lotto.model.customer;

import lotto.model.customer.exception.InvalidPurchaseAmountException;
import lotto.model.lottostore.Price;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PurchaseAmountTest {
    @Test
    void 구입_금액이_1원_이상일_때_확인() {
        assertDoesNotThrow(() -> PurchaseAmount.from(1));
    }

    @Test
    void 구입_금액이_1원_미만일_때_예외_발생() {
        assertThrows(InvalidPurchaseAmountException.class, () -> PurchaseAmount.from(0));
    }

    @Test
    void 구입할_제품의_가격이_0원일_때_예외_발생() {
        assertThrows(InvalidPurchaseAmountException.class, () -> PurchaseAmount.from(100).calculatePurchaseQuantity(Price.FREE_TICKET_PRICE, 0));
    }
}
