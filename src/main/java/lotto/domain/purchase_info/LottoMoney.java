package lotto.domain.purchase_info;

public class LottoMoney {

    private static final int MINIMUM_COST = 1_000;
    private static final int ZERO = 0;

    private final int money;

    public LottoMoney(int money) {
        validate(money);
        this.money = money;
    }

    private void validate(int money) {
        validateNegative(money);
        validateLottoMoney(money);
    }

    private void validateNegative(int lottoMoney) {
        if (lottoMoney < ZERO) {
            throw new IllegalArgumentException("로또 구입 금액은 음수일 수 없습니다.");
        }
    }

    private void validateLottoMoney(int lottoMoney) {
        if (lottoMoney % MINIMUM_COST != ZERO) {
            throw new IllegalArgumentException("로또 구입 금액은 1000원 단위로 입력해야 합니다.");
        }
    }

    public int getLottoPurchaseCounts() {
        return money / MINIMUM_COST;
    }

    public double getMoney() {
        return money;
    }
}
