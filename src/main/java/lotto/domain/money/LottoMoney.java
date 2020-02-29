package lotto.domain.money;

public class LottoMoney {

    private static final String NEGATIVE_MONEY = "로또 구입 금액은 음수일 수 없습니다.";
    private static final String LOTTO_MONEY_NOT_BY_UNIT = "로또 구입 금액은 1000원 단위로 입력해야 합니다.";
    private static final int MINIMUM_COST = 1_000;
    private static final int ZERO = 0;

    private final int money;

    public LottoMoney(int money) {
        validate(money);
        this.money = money;
    }

    private void validate(int money) {
        if (money < ZERO) {
            throw new IllegalArgumentException(NEGATIVE_MONEY);
        }
        if (money % MINIMUM_COST != ZERO) {
            throw new IllegalArgumentException(LOTTO_MONEY_NOT_BY_UNIT);
        }
    }

    public int getLottoPurchaseCounts() {
        return money / MINIMUM_COST;
    }

    public double getMoney() {
        return money;
    }
}
