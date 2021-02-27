package lotto.domain.ticketpurchase;

import lotto.domain.ticket.LottoTicket;

public class PurchasePrice {
    private static final int ZERO = 0;

    private final int purchasePrice;

    public PurchasePrice(int value) {
        validateExactlyDividedByOneTicketPrice(value);
        this.purchasePrice = value;
    }

    private void validateExactlyDividedByOneTicketPrice(int purchasePrice) {
        if (isExactlyDividedByThousand(purchasePrice)) {
            throw new IllegalArgumentException("구입 금액은 1000원 단위여야 합니다.");
        }
    }

    private boolean isExactlyDividedByThousand(int purchasePrice) {
        return purchasePrice <= ZERO || purchasePrice % LottoTicket.PRICE != ZERO;
    }

    public boolean isLessThan(int value) {
        return this.purchasePrice < value;
    }

    public int getPurchasePrice() {
        return this.purchasePrice;
    }
}
