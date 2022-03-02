package lotto.domain;

public class PurchaseAmount {

    private static final int LOTTO_PRICE = 1000;
    private static final int REMAIN_ZERO = 0;

    private final int amount;

    public PurchaseAmount(final int amount) {
        validateUnit(amount);
        this.amount = amount;
    }

    public boolean canPurchase(final int count) {
        return amount >= LOTTO_PRICE * count;
    }

    public int calculateAutoLottoCount(final int count) {
        return (amount - LOTTO_PRICE * count) / LOTTO_PRICE;
    }

    private void validateUnit(final int amount) {
        if (amount % LOTTO_PRICE != REMAIN_ZERO) {
            throw new IllegalArgumentException("[ERROR] 구입금액은 " + LOTTO_PRICE + "원 단위여야 합니다");
        }
    }

    public int getAmount() {
        return amount;
    }
}
