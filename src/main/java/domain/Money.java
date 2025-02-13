package domain;

public class Money {

    private final int amount;

    public Money(final int amount) {
        validateRange(amount);
        validateAmount(amount);
        this.amount = amount;
    }

    public int getCount() {
        return amount / 1_000;
    }

    private void validateAmount(final int amount) {
        if (amount % 1_000 != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1000 단위 숫자로 입력해야 합니다.");
        }
    }

    private void validateRange(final int amount) {
        if (amount < 1_000) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1000원 이상이어야 합니다.");
        }
    }

    public int getAmount() {
        return amount;
    }
}
