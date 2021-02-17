package lotto.domain.primitive;

public class Ticket {

    private static final int TICKET_PRICE = 1000;
    private static final String TICKET_FORMAT_ERROR_MESSAGE = "돈은 %d원 단위여야 합니다.";
    private final int count;

    public Ticket(Money money) {
        validateThousands(money.getValue());
        this.count = money.getValue() / TICKET_PRICE;
    }

    private void validateThousands(final int value) {
        if (value % TICKET_PRICE != 0) {
            throw new IllegalArgumentException(
                String.format(TICKET_FORMAT_ERROR_MESSAGE, TICKET_PRICE));
        }
    }

    public int getCount() {
        return count;
    }

    public int getPrice() {
        return TICKET_PRICE * count;
    }
}
