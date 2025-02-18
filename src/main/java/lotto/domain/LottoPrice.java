package lotto.domain;

public class LottoPrice {

    private static final int UNIT_PRICE_OF_LOTTO = 1000;
    private final int amount;

    public LottoPrice(final int amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    public int calculateLottoCount() {
        return amount / UNIT_PRICE_OF_LOTTO;
    }

    public double divideFrom(final int inputAmount) {
        return (double) inputAmount / amount;
    }

    private void validateAmount(final int amount) {
        if (amount < UNIT_PRICE_OF_LOTTO) {
            throw new IllegalArgumentException("로또 구입 금액은 1000원 이상이어야 합니다.");
        }
    }
}
