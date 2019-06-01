package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PurchasePriceTest {
    @Test
    void 생성() {
        PurchasePrice purchasePrice = new PurchasePrice(15000);
        assertThat(purchasePrice.getCountOfTicket()).isEqualTo(15);
    }

    @Test
    void 최소값보다_작은입력() {
        assertThrows(IllegalArgumentException.class, () -> new PurchasePrice(800));
    }

    @Test
    void 최대값보다_큰입력() {
        assertThrows(IllegalArgumentException.class, () -> new PurchasePrice(1000000));
    }
}
