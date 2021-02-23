package lotto.domain;

public class Ticket {

    public static final int TICKET_PRICE = 1000;
    public static final String TICKET_MINIMUM_PRICE_ERROR_MESSAGE = "돈은 %d원 이상이어야 합니다.";
    private final int count;

    public Ticket(Money money) {
        validateMinimumTicketPrice(money);
        this.count = money.getValue() / TICKET_PRICE;
    }

    private void validateMinimumTicketPrice(final Money money) {
        if (money.getValue() < TICKET_PRICE) {
            throw new IllegalArgumentException(
                String.format(TICKET_MINIMUM_PRICE_ERROR_MESSAGE, TICKET_PRICE));
        }
    }

    public int getCount() {
        return count;
    }

    public int getPrice() {
        return TICKET_PRICE * count;
    }
}
