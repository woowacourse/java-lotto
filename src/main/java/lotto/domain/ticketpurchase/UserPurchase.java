package lotto.domain.ticketpurchase;

public class UserPurchase {
    private static final int ONE_TICKET_PRICE = 1000;
    private static final int ZERO = 0;

    private final int purchasePrice;
    private final int manualTicketNumber;
    private final int autoTicketNumber;

    public UserPurchase(int purchasePrice, int manualTicketNumber) {
        validateExactlyDividedByOneTicketPrice(purchasePrice);
        this.purchasePrice = purchasePrice;
        this.manualTicketNumber = manualTicketNumber;
        this.autoTicketNumber = (purchasePrice / ONE_TICKET_PRICE) - manualTicketNumber;
    }

    public int getPurchasePrice() {
        return this.purchasePrice;
    }

    public int getManualTicketCount() {
        return this.manualTicketNumber;
    }

    public int getAutoTicketCount() {
        return this.autoTicketNumber;
    }

    private void validateExactlyDividedByOneTicketPrice(int purchasePrice) {
        if (purchasePrice <= ZERO || purchasePrice % ONE_TICKET_PRICE != ZERO) {
            throw new IllegalArgumentException("구입 금액은 1000원 단위여야 합니다.");
        }
    }
}
