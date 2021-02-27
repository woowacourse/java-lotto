package lotto.domain.ticketpurchase;

import lotto.domain.ticket.LottoTicket;

public class UserPurchase {
    private final PurchasePrice purchasePrice;
    private final int manualTicketNumber;
    private final int autoTicketNumber;

    public UserPurchase(PurchasePrice purchasePrice, int manualTicketNumber) {
        validateManualTicketPriceNotGreaterThanPurchasePrice(purchasePrice, manualTicketNumber);
        this.purchasePrice = purchasePrice;
        this.manualTicketNumber = manualTicketNumber;
        this.autoTicketNumber = (purchasePrice.getPurchasePrice() / LottoTicket.PRICE) - manualTicketNumber;
    }

    private void validateManualTicketPriceNotGreaterThanPurchasePrice(PurchasePrice purchasePrice, int manualTicketNumber) {
        if (isTotalManualTicketPriceOverPurchasePrice(purchasePrice, manualTicketNumber)) {
            throw new IllegalArgumentException("구입금액에 맞게 입력해 주세요");
        }
    }

    private boolean isTotalManualTicketPriceOverPurchasePrice(PurchasePrice purchasePrice, int manualTicketNumber) {
        return purchasePrice.isLessThan(manualTicketNumber * LottoTicket.PRICE);
    }

    public PurchasePrice getPurchasePrice() {
        return this.purchasePrice;
    }

    public int getManualTicketCount() {
        return this.manualTicketNumber;
    }

    public int getAutoTicketCount() {
        return this.autoTicketNumber;
    }
}
