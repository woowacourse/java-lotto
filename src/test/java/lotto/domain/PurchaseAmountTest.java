package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PurchaseAmountTest {
    @Test
    void 천원_단위가_아닌경우() {
        int purchaseAmount = 1500;
        assertThat(PurchaseAmount.isLottoPurchaseUnit(purchaseAmount)).isFalse();
    }

    @Test
    void 숫자_외_다른_값_입력_시_재입력() {
        String purchaseAmount = "1a가A";
        assertThat(PurchaseAmount.isNumber(purchaseAmount)).isFalse();
    }
}
