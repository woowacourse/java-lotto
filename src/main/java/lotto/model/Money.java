package lotto.model;

/*
 * 사용자가 투입한 금액을 담는 Class
 */
public class Money {
    private static final String ERROR_NEGATIVE = "[ERROR] 구매 금액은 0원보다 커야 합니다";
    private static final String ERROR_TYPE = "[ERROR] 구매 금액은 숫자로만 입력하세요";

    private final int amount;

    private Money(int amount) {
        checkNegative(amount);
        this.amount = amount;
    }

    private void checkNegative(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(ERROR_NEGATIVE);
        }
    }

    public static Money from(String input) {
        try {
            return new Money(Integer.parseInt(input));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_TYPE);
        }
    }

    public boolean isUnit(int unit) {
        return amount % unit == 0;
    }

    public double rate(long numerator) {
        return numerator / (double)this.amount;
    }

    public int countAvailable(int price) {
        return this.amount / price;
    }
}
