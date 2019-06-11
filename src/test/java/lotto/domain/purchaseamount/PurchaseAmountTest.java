package lotto.domain.purchaseamount;

import lotto.domain.lotto.LottoTicket;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PurchaseAmountTest {
    @Test
    void 금액_생성_실패() {
        assertThrows(PurchaseAmountException.class, () -> {
            PurchaseAmount.create("-10");
        });
    }

    @Test
    void 거스름돈_확인() {
        PurchaseAmount purchaseAmount = PurchaseAmount.create("1300");
        PurchaseAmount change = purchaseAmount.buy(LottoTicket.PRICE);
        assertThat(change.getMoney()).isEqualTo(300);
    }
}