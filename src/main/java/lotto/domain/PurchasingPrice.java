package lotto.domain;

public class PurchasingPrice {
    private static final int MINIMUM_TICKET_PRICE = 1000;
    private static final String INVALID_PRICE = "로또 티켓 최소 구매 금액은 1000원입니다.";

    private final int price;

    public PurchasingPrice(int price) {
        validatePrice(price);
        this.price = price;
    }

    private void validatePrice(int price) {
        if (price < MINIMUM_TICKET_PRICE) {
            throw new IllegalArgumentException(INVALID_PRICE);
        }
    }

    public int calculatePurchasableTicketCounts() {
        return price / MINIMUM_TICKET_PRICE;
    }

    public int getPrice() {
        return price;
    }
}
