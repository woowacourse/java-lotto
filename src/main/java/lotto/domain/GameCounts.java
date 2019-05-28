package lotto.domain;

import java.util.Objects;

public class GameCounts {
    private final int gameCounts;

    public GameCounts(PurchaseAmount purchaseAmount) {
        this.gameCounts = purchaseAmount.getCount();
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
