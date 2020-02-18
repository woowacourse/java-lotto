package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PurchaseAmountTest {
    @Test
    void 로또_개수_반환() {
        PurchaseAmount purchaseAmount = new PurchaseAmount("1500");
        assertThat(purchaseAmount.giveLottoPieces()).isEqualTo(1);
    }

    @Test
    void 남은_돈_반환() {
        PurchaseAmount purchaseAmount = new PurchaseAmount("1500");
        assertThat(purchaseAmount.giveChangeMoney()).isEqualTo(500);
    }

    @Test
    void 숫자_외_다른_값_입력_시_재입력() {
        String purchaseAmount = "1a가A";
        assertThatThrownBy(() -> new PurchaseAmount(purchaseAmount))
                .isInstanceOf(NumberFormatException.class)
                .hasMessage("숫자가 아닙니다.");
    }

    @Test
    void 음수인경우() {
        String purchaseAmount = "-3";
        assertThatThrownBy(() -> new PurchaseAmount(purchaseAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("음수입니다.");
    }
}
