package lotto.model;

import java.util.List;

public class WinningResultResponse {
    private final int matchingCount;
    private final long winningAmount;
    private final boolean hasBonus;
    private final int winningCount;

    public WinningResultResponse(int matchingCount, long winningAmount, boolean hasBonus, int winningCount) {
        this.matchingCount = matchingCount;
        this.winningAmount = winningAmount;
        this.hasBonus = hasBonus;
        this.winningCount = winningCount;
    }

    public int getMatchingCount() {
        return matchingCount;
    }

    public long getWinningAmount() {
        return winningAmount;
    }

    public boolean isHasBonus() {
        return hasBonus;
    }

    public int getWinningCount() {
        return winningCount;
    }
}
