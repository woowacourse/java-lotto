package domain;

public class Money {

    public static final String NOT_ENOUGH_MONEY = "구입 금액은 1000원 미만일 수 없습니다.";
    private static final int MINIMUM_PURCHASE_AMOUNT = 1000;

    private int amount;

    public Money(int amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    public int determineQuantity() {
        return amount / MINIMUM_PURCHASE_AMOUNT;
    }

    private void validateAmount(int amount) {
        if (amount < MINIMUM_PURCHASE_AMOUNT) {
            throw new IllegalArgumentException(NOT_ENOUGH_MONEY);
        }
    }
}
