package lotto;

public class Store {

    private static final int OVER_LIMIT_MONEY = 100_000;
    private static final int UNDER_LIMIT_MONEY = 1_000;

    public Store(int money) {
        validateOverLimit(money);
        validateUnderLimit(money);
    }

    private void validateOverLimit(int money) {
        if (money > OVER_LIMIT_MONEY) {
            throw new IllegalArgumentException("입력금액은 100,000원을 넘을 수 없다.");
        }
    }

    private void validateUnderLimit(int money) {
        if (money < UNDER_LIMIT_MONEY) {
            throw new IllegalArgumentException("입력금액은 1,000원 이상이어야 한다.");
        }
    }

}
