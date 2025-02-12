package model;

public class LottoPurchase {

    private Integer amount;

    public static LottoPurchase of(final String input) {
        validateInteger(input);
        return new LottoPurchase(Integer.parseInt(input));
    }

    public LottoPurchase(Integer amount) {
        validateMinAmount(amount);
        validateAmountUnit(amount);
        this.amount = amount;
    }

    private static void validateInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("로또 구입금액은 숫자여야 합니다.");
        }
    }

    private void validateMinAmount(Integer amount) {
        if (amount < 1000) {
            throw new IllegalArgumentException("로또 구입 최소 금액은 1000원 입니다.");
        }
    }

    private void validateAmountUnit(Integer amount) {
        if (amount % 1000 != 0) {
            throw new IllegalArgumentException("로또 구입금액은 1000원 단위여야 합니다.");
        }
    }

    public Integer getAmount() {
        return amount;
    }
}
