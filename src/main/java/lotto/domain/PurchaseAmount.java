package lotto.domain;

public class PurchaseAmount {

    private static final int TICKET_PRICE = 1000;

    private final int amount;

    public PurchaseAmount(int amount) {
        validateMinimumAmount(amount);
        this.amount = calculateActualAmount(amount);
    }

    public boolean canPurchase(int count) {
        return amount >= TICKET_PRICE * count;
    }

    public int calculateAutoLottoCount(int count) {
        return (amount - TICKET_PRICE * count) / TICKET_PRICE;
    }

    public int calculateTheNumberOfTicket() {
        return amount / TICKET_PRICE;
    }

    private void validateMinimumAmount(final int amount) {
        if (amount < TICKET_PRICE) {
            throw new IllegalArgumentException("[ERROR] 구입금액은 " + TICKET_PRICE + "원 이상이어야 합니다");
        }
    }

    private int calculateActualAmount(final int amount) {
        return amount - (amount % TICKET_PRICE);
    }

    public int getAmount() {
        return amount;
    }
}
