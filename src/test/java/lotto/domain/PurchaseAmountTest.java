package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PurchaseAmountTest {
    @Test
    void 천원_단위가_아닌경우() {
        //given
        int purchaseAmount = 1500;

        Assertions.assertThat(PurchaseAmount.isNumber(purchaseAmount)).isFalse();
    }
}
