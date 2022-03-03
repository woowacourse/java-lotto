package lotto.model;

/*
 * 사용자가 투입한 금액을 담는 Class
 */
public class Money {
    private static final String ERROR_NEGATIVE = "[ERROR] 구매 금액은 0원보다 커야 합니다";
    private static final String ERROR_TYPE = "[ERROR] 구매 금액은 숫자로만 입력하세요";
    private static final String ERROR_UNIT = "[ERROR] 구매 금액은 1000원 단위로 입력하세요";
    private static final int PRICE_LOTTO = 1000;

    private final int amount;
    private int purchasedAmount;

    private Money(int amount) {
        validate(amount);
        this.amount = amount;
        this.purchasedAmount = 0;
    }

    private void validate(int amount) {
        checkNegative(amount);
        checkUnit(amount);
    }

    private void checkNegative(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(ERROR_NEGATIVE);
        }
    }

    private static void checkUnit(int amount) {
        if (amount % PRICE_LOTTO != 0) {
            throw new IllegalArgumentException(ERROR_UNIT);
        }
    }

    public static Money from(String input) {
        try {
            return new Money(Integer.parseInt(input));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_TYPE);
        }
    }

    public int countAvailableLotto() {
        return this.amount / PRICE_LOTTO;
    }

    public void payLotto(int count) {
        this.purchasedAmount = this.purchasedAmount + (PRICE_LOTTO * count);
    }

    public void payLotto() {
        payLotto(1);
    }

    public boolean isLottoAvailable(int count) {
        return (this.amount - this.purchasedAmount) >= (PRICE_LOTTO * count);
    }

    public boolean isLottoAvailable() {
        return isLottoAvailable(1);
    }

    public double rate(long numerator) {
        return numerator / (double)this.amount;
    }
}
