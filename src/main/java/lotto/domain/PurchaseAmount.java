package lotto.domain;

public class PurchaseAmount {
    private static final int LOTTO_UNIT_PRICE = 1_000;
    private static final int MAX_PURCHASE_AMOUNT = 100_000;

    private final int purchaseAmount;

    public PurchaseAmount(final int purchaseAmount) {
        validatePurchaseAmount(purchaseAmount);
        this.purchaseAmount = purchaseAmount;
    }

    private void validatePurchaseAmount(final int purchaseAmount) {
        if (purchaseAmount % LOTTO_UNIT_PRICE != 0) {
            throw new IllegalArgumentException("구입금액은 %d원으로 나누어져야 합니다.".formatted(LOTTO_UNIT_PRICE));
        }

        if (purchaseAmount <= 0) {
            throw new IllegalArgumentException("구입금액은 양수여야 합니다.");
        }

        if (purchaseAmount > MAX_PURCHASE_AMOUNT) {
            throw  new IllegalArgumentException("구입금액은 최대 %d원까지입니다.".formatted(MAX_PURCHASE_AMOUNT));
        }
    }

    public int calculateLottoAmount() {
        return purchaseAmount / LOTTO_UNIT_PRICE;
    }
}
