package domain;

public class PurchaseAmount {
    private int amount;

    public PurchaseAmount(String inputMoney) {
        checkNotNumber(inputMoney);
        this.amount = Integer.parseInt(inputMoney);
        checkNegativeAmount(amount);
    }

    private void checkNotNumber(String inputMoney) {
        try {
            Integer.parseInt(inputMoney);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("구매 금액은 숫자여야합니다.");
        }
    }

    private void checkNegativeAmount(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("구매 금액은 음수일 수 없습니다.");
        }
    }
}
