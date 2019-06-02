package lotto.domain;

import lotto.exception.IllegalNumberBoundException;

import java.util.Objects;

public class Money {
    // TODO 이름 바꿔...
    private static final int DIVIDING_STANDARD = 1000;
    private static final int PERCENT = 100;

    private final int price;
    private int profit = 0;

    public Money(final int price) {
        this.price = price;

        validateMinimumMoneyInput(this.price);
    }

    public int getTicketCount() {
        return price / DIVIDING_STANDARD;
    }

    public void addProfit(RankType rankType) {
        this.profit += rankType.getPrize();
    }

    public double getProfitRate() {
        return (double) this.profit / this.price * PERCENT;
    }

    private void validateMinimumMoneyInput(int money) {
        if (money < DIVIDING_STANDARD) {
            throw new IllegalNumberBoundException("최소 로또 구매 금액은 1000원입니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money1 = (Money) o;
        return price == money1.price &&
                profit == money1.profit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, profit);
    }
}
