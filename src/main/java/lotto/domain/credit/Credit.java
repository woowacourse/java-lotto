package lotto.domain.credit;

public class Credit {

    private static final int CREDIT_BASE_UNIT = 1000;
    private static final String CREDIT_NOT_DIVISIBLE_EXCEPTION_MESSAGE = "구입 금액은 1000으로 나누어 떨어져야 합니다.";

    private final int money;

    public Credit(final int money) {
        validateMoneyIsDivisible(money);
        this.money = money;
    }

    private void validateMoneyIsDivisible(final int money) {
        if (money % CREDIT_BASE_UNIT != 0) {
            throw new IllegalArgumentException(CREDIT_NOT_DIVISIBLE_EXCEPTION_MESSAGE);
        }
    }

    public int getQuotient() {
        return money / CREDIT_BASE_UNIT;
    }

    public int getMoney() {
        return money;
    }

}
