package lotto.domain;

public class UserPurchase {
    private static final int ONE_TICKET_PRICE = 1000;
    private final int purchasePrice;
    private final int numberOfTicket;

    public UserPurchase(int purchasePrice) {
        validateExactlyDividedByOneTicketPrice(purchasePrice);
        this.purchasePrice = purchasePrice;
        this.numberOfTicket = purchasePrice / ONE_TICKET_PRICE;
    }

    private void validateExactlyDividedByOneTicketPrice(int purchasePrice) {
        if (purchasePrice % ONE_TICKET_PRICE != 0) {
            throw new IllegalArgumentException("구입 금액은 1000원 단위여야 합니다.");
        }
    }

    public int getNumberOfTicket() {
        return this.numberOfTicket;
    }

    public int getPurchasePrice() {
        return this.purchasePrice;
    }
}
