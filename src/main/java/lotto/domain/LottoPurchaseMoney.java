package lotto.domain;

public class LottoPurchaseMoney {

    private static final int LOTTO_PRICE = 1_000;
    private static final int MINIMUM_LOTTO_AMOUNT = 0;

    private final int money;

    public LottoPurchaseMoney(int money) {
        validateUnderLimit(money);
        validateUnderThousands(money);
        this.money = money;
    }

    public boolean isPurchaseLotto(int amount) {
        if (this.money >= amount * LOTTO_PRICE) {
            return true;
        }
        throw new IllegalArgumentException("구매할 수 있는 수량이어야한다.");
    }

    public int calculateAvailablePurchaseAmount(int nowAmount) {
        return this.money / LOTTO_PRICE - nowAmount;
    }

    public int getMoney() {
        return this.money;
    }

    private void validateUnderLimit(int money) {
        if (money < LOTTO_PRICE) {
            throw new IllegalArgumentException("입력금액은 1,000원 이상이어야 한다.");
        }
    }

    private void validateUnderThousands(int money) {
        if (money % LOTTO_PRICE != MINIMUM_LOTTO_AMOUNT) {
            throw new IllegalArgumentException("입력금액은 1,000원 단위어야 한다.");
        }
    }
}
