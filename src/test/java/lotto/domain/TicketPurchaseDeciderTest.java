package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.domain.vo.ManualTicketCount;
import lotto.domain.vo.PurchaseAmount;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TicketPurchaseDeciderTest {

    @Test
    @DisplayName("구매 금액이 1000원이고 수동 구매를 1장을 하면, 자동티켓은 0장이다")
    void buy_manual_one() {
    	// given
        TicketPurchaseDecider ticketPurchaseDecider = getTicketPurchaseDecider("1000", "1");

        // when
        int autoTicketCount = ticketPurchaseDecider.getAutoTicketCount();

    	// then
        assertThat(autoTicketCount).isEqualTo(0);
    }

    @Test
    @DisplayName("3000원을 넣고 수동 구매를 1장 하면, 자동 티켓은 2장이다")
    void buy_manual_1_auto_2() {
    	// given
        TicketPurchaseDecider ticketPurchaseDecider = getTicketPurchaseDecider("3000", "1");

    	// when
        int autoTicketCount = ticketPurchaseDecider.getAutoTicketCount();

    	// then
        assertThat(autoTicketCount).isEqualTo(2);
    }

    @Test
    @DisplayName("2000원을 넣고 수동 구매를 0장하면, 자동티켓은 2장이다")
    void buy_manual_0_auto_2() {
    	// given
        TicketPurchaseDecider ticketPurchaseDecider = getTicketPurchaseDecider("2000", "0");

    	// when
        int autoTicketCount = ticketPurchaseDecider.getAutoTicketCount();

    	// then
        assertThat(autoTicketCount).isEqualTo(2);
    }

    private TicketPurchaseDecider getTicketPurchaseDecider(String moneyString, String manualTicketCountString) {
        PurchaseAmount purchaseAmount = new PurchaseAmount(moneyString);
        ManualTicketCount manualTicketCount = new ManualTicketCount(manualTicketCountString, purchaseAmount);
        return new TicketPurchaseDecider(purchaseAmount, manualTicketCount);
    }
}
