package lotto.domain;

public class Money implements Comparable<Money> {
    private static final String ERROR_ONLY_NATURAL_NUMBER = "금액은 음수가 될 수 없습니다.";

    private int money;

    public Money(final int money) {
        validateMoney(money);
        this.money = money;
    }

    public void add(final Money money) {
        validateMoney(this.money + money.money);
        this.money += money.money;
    }

    public void subtract(final Money money) {
        validateMoney(this.money - money.money);
        this.money -= money.money;
    }

    public void multiply(final int count) {
        validateMoney(this.money * count);
        this.money *= count;
    }

    public double calculateProfitRate(Money payment) {
        return (double)this.money / payment.money;
    }

    private void validateMoney(final int money) {
        if (money < 0) {
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
