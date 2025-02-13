package model;

public class Purchase {
    private static final int LOTTO_PRICE = 1000;
    private static final int MIN_PURCHASE_AMOUNT = 1000;
    private static final int MAX_PURCHASE_AMOUNT = 100000;

    private final int amount;

    public Purchase(String purchaseAmountInput) {
        validate(purchaseAmountInput);
        this.amount = Integer.parseInt(purchaseAmountInput);
    }

    public int calculateLottoCount() {
        return amount / LOTTO_PRICE;
    }

    public int getAmount() {
        return amount;
    }

    private void validate(String purchaseAmountInput) {
        try {
            int purchaseAmount = Integer.parseInt(purchaseAmountInput);
            if (purchaseAmount < MIN_PURCHASE_AMOUNT
                    || purchaseAmount > MAX_PURCHASE_AMOUNT
                    || purchaseAmount % LOTTO_PRICE != 0) {
                throw new IllegalArgumentException("1000 이상 100000 이하의 1000으로 나누어 떨어지는 정수를 입력해주세요.");
            }
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("구입 금액은 정수로 입력해주세요.");
        }
    }
}
