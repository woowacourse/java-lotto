package lotto.domain.ticketpurchase;

public class UserPurchase {
    private static final int ONE_TICKET_PRICE = 1000;

    private final int purchasePrice;
    private final int numberOfTickets;

    public UserPurchase(int purchasePrice) throws IllegalArgumentException {
        validateExactlyDividedByOneTicketPrice(purchasePrice);
        this.purchasePrice = purchasePrice;
        this.numberOfTickets = purchasePrice / ONE_TICKET_PRICE;
    }

    public int getNumberOfTickets() {
        return this.numberOfTickets;
    }

    public int getPurchasePrice() {
        return this.purchasePrice;
    }

    private void validateExactlyDividedByOneTicketPrice(int purchasePrice) throws IllegalArgumentException {
        if (purchasePrice <= 0 || (purchasePrice % ONE_TICKET_PRICE) != 0) {
            throw new IllegalArgumentException("구입 금액은 1000원 단위여야 합니다.");
        }
    }
}
