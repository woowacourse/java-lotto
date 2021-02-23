package lottogame.domain;

import lottogame.utils.InvalidMoneyException;

import java.util.Objects;

public class Money {
    private final int money;

    public Money(int money) {
        validate(money);
        this.money = money;
    }

    private void validate(int money) {
        if (money < 0) {
            throw new InvalidMoneyException();
        }
    }

    public float divide(int totalPrizeMoney) {
        return (float) totalPrizeMoney / money;
    }

    public int buyLotto(int lottoPrice) {
        return money / lottoPrice;
    }

    public boolean availableForPurchase(int purchaseMoney) {
        return money >= purchaseMoney;
    }

    public Money consume(int price) {
        return new Money(money - price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money1 = (Money) o;
        return money == money1.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }
}
