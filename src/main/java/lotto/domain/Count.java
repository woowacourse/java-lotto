package lotto.domain;

public class Count {
    protected static final String ERROR_NOT_INTEGER = "[ERROR] 수동으로 구매할 횟수는 숫자로 입력해주세요";
    protected static final String ERROR_SHORT_MONEY = "[ERROR] 금액이 모자랍니다, 수동 구매횟수를 줄여주세요.";
    private final int count;

    public Count(Money money, int value) {
        checkShortMoney(money, value);
        this.count = value;
    }

    public Count(int count) {
        this.count = count;
    }

    private void checkShortMoney(Money money, int value) {
        if (isShortMoney(money, value)) {
            throw new IllegalArgumentException(ERROR_SHORT_MONEY);
        }
    }

    private boolean isShortMoney(Money money, int value) {
        return Money.UNIT_AMOUNT * value > money.getAmount();
    }

    public int getCount() {
        return count;
    }
}
