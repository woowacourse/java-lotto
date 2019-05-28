package lotto.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PurchaseAmountTest {
    @Test
    void 구입_금액이_1원_이상일_때_확인() {
        assertDoesNotThrow(() -> new PurchaseAmount(1));
    }

    @Test
    void 구입_금액이_1원_미만일_때_예외_발생() {
        assertThrows(IllegalArgumentException.class, () -> new PurchaseAmount(0));
    }

    @Test
    void 구입_가능_개수가_1개_이상일_때_확인(){
        assertThat(new PurchaseAmount(1000).calculatePurchaseQuantity(1000)).isEqualTo(1);
    }

    @Test
    void 구입_가능_개수가_1개_미만일_때_예외_발생() {
        assertThrows(IllegalArgumentException.class, () -> new PurchaseAmount(100).calculatePurchaseQuantity(101));
    }
}
