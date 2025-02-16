package lotto.domain;

import lotto.domain.constant.LottoConstants;

public class PurchaseAmount {

    private final long amount;

    public PurchaseAmount(long purchaseAmount) {
        validatePositive(purchaseAmount);
        validateAmount(purchaseAmount);
        this.amount = purchaseAmount;
    }

    public long getAmount() {
        return amount;
    }

    public int countNumberOfPurchases() {
        return (int) amount / LottoConstants.LOTTO_PRICE;
    }

    private void validateAmount(long purchaseAmount) {
        if (purchaseAmount % LottoConstants.LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("구매 금액은 " + LottoConstants.LOTTO_PRICE + "원 단위어야 합니다.");
        }
    }

    private void validatePositive(long purchaseAmount) {
        if (purchaseAmount <= 0) {
            throw new IllegalArgumentException("구매 금액은 양수여야 합니다.");
        }
    }
}
