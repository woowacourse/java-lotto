package lotto.domain;

public class PurchasingPrice {
    private static final int ZERO = 0;
    private static final String INVALID_PRICE = "로또 티켓 구매 금액은 양의 정수이어야 합니다.";
    private static final String INVALID_TICKET_COST = "티켓 가격은 양의 정수여야 합니다.";
    private final int price;

    public PurchasingPrice(int price) {
        validatePrice(price);
        this.price = price;
    }

    private void validatePrice(int price) {
        if (price <= ZERO) {
            throw new IllegalArgumentException(INVALID_PRICE);
        }
    }

    public int calculatePurchasableTicketCounts(int ticketCost) {
        if (ticketCost <= ZERO) {
            throw new IllegalArgumentException(INVALID_TICKET_COST);
        }
        return price / ticketCost;
    }

    public int getPrice() {
        return price;
    }
}
