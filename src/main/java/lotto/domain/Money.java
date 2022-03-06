package lotto.domain;

public class Money implements Comparable<Money> {
    private static final String ERROR_ONLY_NATURAL_NUMBER = "금액은 음수가 될 수 없습니다.";

    private long money;

    public Money() {
        this.money = 0;
    }

    public Money(final long money) {
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

    private void validateMoney(final long money) {
        if (money < 0) {
            throw new IllegalArgumentException(ERROR_ONLY_NATURAL_NUMBER);
        }
    }

    public long getMoney() {
        return money;
    }

    @Override
    public int compareTo(Money o) {
        return Long.compare(this.money, o.money);
    }
}
