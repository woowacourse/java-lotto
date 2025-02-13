package domain;

public class Ticket {

    private static final int TICKET_PRICE = 1_000;

    private final int quantity;

    private Ticket(int quantity) {
        this.quantity = quantity;
    }

    public static Ticket create(int price) {
        int quantity = buy(price);
        return new Ticket(quantity);
    }

    private static int buy(int price) {
        return price / TICKET_PRICE;
    }

    public int getQuantity() {
        return quantity;
    }

}
