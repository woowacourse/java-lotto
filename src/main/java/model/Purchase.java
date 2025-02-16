package model;

public class Purchase {
    private static final int LOTTO_PRICE = 1000;
    private static final int MIN_PURCHASE_AMOUNT = 1000;
    private static final int MAX_PURCHASE_AMOUNT = 100000;

    private final int amount;

    public Purchase(int amount) {
        validateRange(amount);
        this.amount = amount;
    }

    public int calculateLottoCount() {
        return amount / LOTTO_PRICE;
    }

    public int getAmount() {
        return amount;
    }

    private void validateRange(int amount) {
        if (amount < MIN_PURCHASE_AMOUNT || amount > MAX_PURCHASE_AMOUNT) {
            throw new IllegalArgumentException("1000 이상 100000 이하의 정수를 입력해주세요.");
        }
        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("1000으로 나누어 떨어지는 정수를 입력해주세요.");
        }
    }
}
