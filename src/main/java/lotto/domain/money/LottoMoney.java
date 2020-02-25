package lotto.domain.money;

public class LottoMoney {

    private static final int MINIMUM_COST = 1_000;
    private static final int REMINDER = 0;

    private final int money;

    public LottoMoney(int money) {
        validateMinimumCost(money);
        validateLottoMoney(money);
        this.money = money;
    }

    private void validateLottoMoney(int lottoMoney) {
        if (lottoMoney % MINIMUM_COST != REMINDER) {
            throw new IllegalArgumentException("로또 구매는 1000원 단위로만 가능합니다.");
        }
    }

    private void validateMinimumCost(int lottoMoney) {
        if (lottoMoney < MINIMUM_COST) {
            throw new IllegalArgumentException("로또 최소 구매 금액은 1000원입니다.");
        }
    }

    public int getLottoPurchaseCounts() {
        return money / MINIMUM_COST;
    }

    public double getMoney() {
        return money;
    }
}
