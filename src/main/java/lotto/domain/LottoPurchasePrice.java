package lotto.domain;

import lotto.domain.exception.InvalidLottoPriceException;

import java.util.Objects;

public class LottoPurchasePrice {
    private static final int LOWER_BOUND_OF_PRICE = 1000;
    private final int purchasePrice;

    public LottoPurchasePrice(int purchasePrice) {
        if (isDeficient(purchasePrice)) {
            throw new InvalidLottoPriceException("로또의 최소 구매 가격은 " + LOWER_BOUND_OF_PRICE + "원입니다.");
        }

        this.purchasePrice = purchasePrice;
    }

    private boolean isDeficient(int purchasePrice) {
        return purchasePrice < LOWER_BOUND_OF_PRICE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final LottoPurchasePrice that = (LottoPurchasePrice) o;
        return purchasePrice == that.purchasePrice;
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchasePrice);
    }
}
