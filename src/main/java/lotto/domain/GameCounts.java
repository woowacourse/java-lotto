package lotto.domain;

import java.util.Objects;

import lotto.exceptions.InvalidPurchaseAmountException;

public class GameCounts {
    private static final String INVALID_PURCHASE_AMOUNT = "구매 금액은 1000원 단위입니다.";
    private static final int LOTTO_AMOUNT_UNIT = 1000;
    private static final int PROPER_REMINDER = 0;

    private final int gameCounts;

    public GameCounts(int purchaseAmount) {
        valid(purchaseAmount);
        this.gameCounts = purchaseAmount / LOTTO_AMOUNT_UNIT;
    }

    private void valid(int purchaseAmount) {
        if (!isProperAmount(purchaseAmount)) {
            throw new InvalidPurchaseAmountException(INVALID_PURCHASE_AMOUNT);
        }
    }

    private boolean isProperAmount(int purchaseAmount) {
        return purchaseAmount % LOTTO_AMOUNT_UNIT == PROPER_REMINDER;
    }

    public int getGameCounts() {
        return gameCounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameCounts that = (GameCounts) o;
        return gameCounts == that.gameCounts;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameCounts);
    }
}
