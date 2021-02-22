package lotto.domain.ticketpurchase;

public class UserPurchase {
    private static final int ONE_TICKET_PRICE = 1000;
    private static final int ZERO = 0;

    private final int purchasePrice;
    private final int numberOfTicket;

    public UserPurchase(int purchasePrice) {
        validateExactlyDividedByOneTicketPrice(purchasePrice);
        this.purchasePrice = purchasePrice;
        this.numberOfTicket = purchasePrice / ONE_TICKET_PRICE;
    }

    public int getNumberOfTicket() {
        return this.numberOfTicket;
    }

    public int getPurchasePrice() {
        return this.purchasePrice;
    }

    private void validateExactlyDividedByOneTicketPrice(int purchasePrice) {
        if (purchasePrice <= ZERO || purchasePrice % ONE_TICKET_PRICE != ZERO) {
            throw new IllegalArgumentException("구입 금액은 1000원 단위여야 합니다.");
        }
    }
}
