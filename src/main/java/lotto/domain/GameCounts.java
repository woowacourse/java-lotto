package lotto.domain;

import java.util.Objects;

import lotto.exceptions.InvalidPurchaseAmountException;

public class GameCounts {
    private final int gameCounts;

    public GameCounts(int purchaseAmount) {
        if (purchaseAmount % 1000 != 0) {
            throw new InvalidPurchaseAmountException("구매 금액은 1000원 단위입니다.");
        }
        this.gameCounts = purchaseAmount / 1000;
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
