package lotto.domain.ticketpurchase;

public class PurchasePrice {
    private static final int ONE_TICKET_PRICE = 1000;

    private final int purchasePrice;

    public PurchasePrice(int purchasePrice) {
        validateExactlyDividedByOneTicketPrice(purchasePrice);
        this.purchasePrice = purchasePrice;
    }

    private void validateExactlyDividedByOneTicketPrice(int purchasePrice) {
        if (purchasePrice <= 0 || (purchasePrice % ONE_TICKET_PRICE) != 0) {
            throw new IllegalArgumentException("구입 금액은 1000원 단위여야 합니다.");
        }
    }

    public int getPurchasePrice() {
        return purchasePrice;
    }

    public int getNumberOfAllTicketsToPurchase() {
        return purchasePrice / ONE_TICKET_PRICE;
    }
}
