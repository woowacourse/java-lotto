package lotto.domain;

public class Money implements Comparable<Money> {
    private static final String ERROR_ONLY_NATURAL_NUMBER = "금액은 음수가 될 수 없습니다.";

    private int money;

    public Money(final int money) {
        validateMoney(money);
        this.money = money;
    }

    public void add(final int money) {
        validateMoney(this.money + money);
        this.money += money;
    }

    public void subtract(final int money) {
        validateMoney(this.money - money);
        this.money -= money;
    }

    public void multiply(final int count) {
        validateMoney(this.money * count);
        this.money *= count;
    }

    private void validateMoney(final int purchaseAmount) {
        if (purchaseAmount < 0) {
            throw new IllegalArgumentException(ERROR_ONLY_NATURAL_NUMBER);
        }
    }

    public int getMoney() {
        return money;
    }

    @Override
    public int compareTo(Money o) {
        return this.money - o.money;
    }
}
