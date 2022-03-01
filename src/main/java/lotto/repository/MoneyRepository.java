package lotto.repository;

import lotto.domain.Money;

public class MoneyRepository {

    private Money money = new Money(0);

    private MoneyRepository() {
    }

    private static class MoneyRepositoryHelper {
        private static final MoneyRepository INSTANCE = new MoneyRepository();
    }

    public static MoneyRepository getInstance() {
        return MoneyRepositoryHelper.INSTANCE;
    }

    public void save(Money other) {
        synchronized (this) {
            this.money = new Money(other.getAmount());
        }
    }

    public Money get() {
        synchronized (this) {
            return new Money(money.getAmount());
        }
    }
}
