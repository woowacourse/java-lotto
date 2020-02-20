package domain;

public class Money {
    private static final String ERROR_NOT_POSITIVE_MESSAGE = "금액은 0 이하로 입력될 수 없습니다.";
    private static final String ERROR_SURPLUS_MESSAGE = "잔돈이 남습니다. 결제할 수 없습니다.";
    private static final int LOTTO_PRICE = 1000;

    private int money;

    public Money(int money) {
        validatePositiveMoney(money);
        validateSurplusMoney(money);
        this.money = money;
    }

    private void validateSurplusMoney(int money) {
        if (money % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(ERROR_SURPLUS_MESSAGE);
        }
    }

    private void validatePositiveMoney(int money) {
        if (money <= 0) {
            throw new IllegalArgumentException(ERROR_NOT_POSITIVE_MESSAGE);
        }
    }

    public long calculateEarningRate(long earningMoney) {
        return 100 * earningMoney / money;
    }

    public int countGames() {
        return money / LOTTO_PRICE;
    }
}
