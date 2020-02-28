package lotto.domain.money;

public class LottoMoney {

    private static final int MINIMUM_COST = 1_000;
    private static final int ZERO = 0;

    private final int money;

    public LottoMoney(int money) {
        validate(money);
        this.money = money;
    }

    private void validate(int money) {
        if (money < ZERO) {
            throw LottoMoneyException.negative();
        }
        if (money % MINIMUM_COST != ZERO) {
            throw LottoMoneyException.notByUnit();
        }
    }

    public int getLottoPurchaseCounts() {
        return money / MINIMUM_COST;
    }

    public double getMoney() {
        return money;
    }
}
