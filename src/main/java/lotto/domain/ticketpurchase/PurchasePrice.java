package lotto.domain.ticketpurchase;

import lotto.domain.exception.InvalidPurchasePriceException;

public class PurchasePrice {
    private static final int ONE_TICKET_PRICE = 1000;

    private final int price;

    public PurchasePrice(int price) {
        validate(price);
        this.price = price;
    }

    private void validate(int price) {
        if (price <= 0 || (price % ONE_TICKET_PRICE) != 0) {
            throw new InvalidPurchasePriceException();
        }
    }

    public int getPrice() {
        return price;
    }

    public int allTicketsSize() {
        return price / ONE_TICKET_PRICE;
    }
}
