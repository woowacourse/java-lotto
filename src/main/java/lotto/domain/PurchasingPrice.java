package lotto.domain;

public class PurchasingPrice {
    private static final int MINIMUM_TICKET_PRICE = 1000;
    private static final String INVALID_TICKET_PRICE = "로또 티켓 최소 구매 금액은 1000원입니다.";

    private final int purchasingPrice;

    public PurchasingPrice(int purchasingPrice) {
        validatePrice(purchasingPrice);
        this.purchasingPrice = purchasingPrice;
    }

    private void validatePrice(int purchasingPrice) {
        if (purchasingPrice < MINIMUM_TICKET_PRICE) {
            throw new IllegalArgumentException(INVALID_TICKET_PRICE);
        }
    }

    public int calculatePurchasableTicketCounts() {
        return purchasingPrice / MINIMUM_TICKET_PRICE;
    }

    public int getPurchasingPrice() {
        return purchasingPrice;
    }
}
