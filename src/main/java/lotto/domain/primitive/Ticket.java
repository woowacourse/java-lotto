package lotto.domain.primitive;

public class Ticket {

    private static final int TICKET_PRICE = 1000;
    private final int count;

    public Ticket(Money money) {
        validateMinimumTicketPrice(money);
        this.count = money.getValue() / TICKET_PRICE;
    }

    private void validateMinimumTicketPrice(final Money money) {
        if (money.getValue() < TICKET_PRICE) {
            throw new IllegalArgumentException(
                    String.format("돈은 %d원 이상이어야 합니다.", TICKET_PRICE));
        }
    }

    public int getCount() {
        return count;
    }

    public int getPrice() {
        return TICKET_PRICE * count;
    }
}
