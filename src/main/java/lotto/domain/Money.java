package lotto.domain;

public class Money {
    private static final int LOTTO_PRICE = 1000;
    private final int amount;

    public Money(final int amount) {
        this.amount = amount;
    }

    public int getLottoTicketCount() {
        return amount / LOTTO_PRICE;
    }

    public int getAmount() {
        return amount;
    }
}


