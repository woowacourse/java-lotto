package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PurchaseAmountTest {
    @Test
    void 로또_개수_반환() {
        int purchaseAmount = 1500;
        assertThat(PurchaseAmount.giveLottoPieces(purchaseAmount)).isEqualTo(1);
    }

    @Test
    void 남은_돈_반환() {
        int purchaseAmount = 1500;
        assertThat(PurchaseAmount.giveChangeMoney(purchaseAmount)).isEqualTo(500);
    }

    @Test
    void 숫자_외_다른_값_입력_시_재입력() {
        String purchaseAmount = "1a가A";
        assertThat(PurchaseAmount.isNumber(purchaseAmount)).isFalse();
    }

    @Test
    void 음수인경우() {
        int purchaseAmount = -3;
        assertThat(PurchaseAmount.isPositiveNumber(purchaseAmount)).isFalse();
    }
}
