package domain.lotto;

import static domain.lotto.Lotto.LOTTO_PRICE;

public class PurchaseAmount {

    private final int money;

    public PurchaseAmount(final int money) {
        validatePurchaseAmountRange(money);
        validatePurchaseAmountDividedByUnit(money);
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public int getLottoQuantity() {
        return money / LOTTO_PRICE;
    }
    
    private void validatePurchaseAmountDividedByUnit(int value) {
        if (value % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("구입 금액은" + LOTTO_PRICE + "원 단위로 가능합니다.");
        }
    }

    private void validatePurchaseAmountRange(int value) {
        if (value < LOTTO_PRICE) {
            throw new IllegalArgumentException("구입 금액은 " + LOTTO_PRICE + "원 이상부터 가능합니다.");
        }
    }

}
