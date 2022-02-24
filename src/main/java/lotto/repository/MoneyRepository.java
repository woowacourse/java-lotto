package lotto.repository;

import lotto.domain.Money;

public class MoneyRepository {

    private static Money money = new Money(0);

    public void save(Money other) {
        money = other;
    }

    public Money get() {
        return new Money(money.getAmount());
    }
}
