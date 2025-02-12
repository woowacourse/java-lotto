package model;

public class Purchasement {
    private final int amount;

    public Purchasement(String purchaseAmountInput) {
        try {
            int purchaseAmount = Integer.parseInt(purchaseAmountInput);
            if (purchaseAmount < 1000 || purchaseAmount > 100000 || purchaseAmount % 1000 != 0) {
                throw new IllegalArgumentException("1000 이상 100000 이하의 1000으로 나누어 떨어지는 정수를 입력해주세요.");
            }
            this.amount = purchaseAmount;
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("구입 금액은 정수로 입력해주세요.");
        }
    }

    public int calculateLottoCount() {
        return amount / 1000;
    }

    public int getAmount() {
        return amount;
    }
}
