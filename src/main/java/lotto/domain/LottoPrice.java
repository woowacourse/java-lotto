package lotto.domain;

public class LottoPrice {

    private static final int MINIMUM_PRICE = 1000;

    private final int amount;

    public LottoPrice(final int amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    private void validateAmount(final int amount) {
        if (amount < MINIMUM_PRICE) {
            throw new IllegalArgumentException("로또 구입 금액은 1000원 이상이어야 합니다.");
        }
    }
}
