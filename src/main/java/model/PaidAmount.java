package model;

public class PaidAmount {

    private static final int VALID_UNIT = 1_000;
    private final int amount;

    public PaidAmount(int amount) {
        validateUnit(amount);
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public int getUnitCount() {
        return amount / VALID_UNIT;
    }

    private void validateUnit(int amount) {
        if (amount % VALID_UNIT != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1000원 단위여야 합니다.");
        }
    }
}
