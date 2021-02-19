package lotto.domain.number;

public class Payout {

    private static final int PRICE_MINIMUM = 0;

    private final int amount;

    private Payout(int amount) {
        validatePositive(amount);
        this.amount = amount;
    }

    public static Payout valueOf(String amount) {
        return new Payout(validateNumeric(amount));
    }

    private static int validateNumeric(String amount) {
        try {
            return Integer.parseInt(amount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("입력이 숫자가 아니거나 범위를 벗어났습니다.");
        }
    }

    private static void validatePositive(int amount) {
        if (amount <= PRICE_MINIMUM) {
            throw new IllegalArgumentException("입력값이 양수가 아닙니다.");
        }
    }

    public int getAmount() {
        return amount;
    }

    public int getNumberOfStuff(int stuffPrice) {
        return amount / stuffPrice;
    }
}