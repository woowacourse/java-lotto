package domain;

import static error.ErrorMessage.INVALID_TICKET_PRICE;

public class TicketMachine {
    private static final int TICKET_PRICE = 1_000;
    private final int quantity;

    private TicketMachine(int quantity) {
        this.quantity = quantity;
    }

    public static TicketMachine create(int price) {
        validatePrice(price);
        int quantity = calculateQuantity(price);
        return new TicketMachine(quantity);
    }

    public int getQuantity() {
        return quantity;
    }

    private static int calculateQuantity(int price) {
        return price / TICKET_PRICE;
    }

    private static void validatePrice(int price) {
        if (price % TICKET_PRICE == 0) {
            return;
        }
        throw new IllegalArgumentException(INVALID_TICKET_PRICE.getMessage());
    }
}
