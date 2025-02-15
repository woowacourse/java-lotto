package domain;

import static error.ErrorMessage.INVALID_TICKET_PRICE;

public class Ticket {

    private static final int TICKET_PRICE = 1_000;

    private final int quantity;

    private Ticket(int quantity) {
        this.quantity = quantity;
    }

    public static Ticket create(int price) {
        validatePrice(price);
        int quantity = buy(price);
        return new Ticket(quantity);
    }

    public int getQuantity() {
        return quantity;
    }

    private static int buy(int price) {
        return price / TICKET_PRICE;
    }

    private static void validatePrice(int price) {
        if (price % TICKET_PRICE == 0) {
            return;
        }
        throw new IllegalArgumentException(INVALID_TICKET_PRICE.getMessage());
    }

}
