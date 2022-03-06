package lotto.domain;

import lotto.domain.vo.ManualTicketCount;
import lotto.domain.vo.PurchaseAmount;

public class TicketPurchaseDecider {

    private int manualTicketCount;
    private int autoTicketCount;

    public TicketPurchaseDecider(PurchaseAmount purchaseAmount, ManualTicketCount manualTicketCount) {
        calculateTicketSize(purchaseAmount, manualTicketCount);
    }

    private void calculateTicketSize(PurchaseAmount purchaseAmount, ManualTicketCount manualTicketCount) {
        int totalTicketCount = purchaseAmount.availableTicketCanBuy();
        this.manualTicketCount = manualTicketCount.ticketNumber();
        this.autoTicketCount = totalTicketCount - this.manualTicketCount;
    }

    public int getManualTicketCount() {
        return manualTicketCount;
    }

    public int getAutoTicketCount() {
        return autoTicketCount;
    }
}
