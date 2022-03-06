package lotto.dto;

import lotto.domain.matchkind.WinningKind;

public class WinningKindDto {
    private final int matchedCount;
    private final boolean matchedBonus;
    private final long winningAmount;
    private final int winningCount;

    public WinningKindDto(final WinningKind winningKind, final int winningCount) {
        this.matchedCount = winningKind.getMatchCount();
        this.matchedBonus = winningKind.hasSameNumberWithBonus();
        this.winningAmount = winningKind.getWinningAmount();
        this.winningCount = winningCount;
    }

    public int getMatchedCount() {
        return matchedCount;
    }

    public boolean hasMatchedBonus() {
        return matchedBonus;
    }

    public long getWinningAmount() {
        return winningAmount;
    }

    public int getWinningCount() {
        return winningCount;
    }
}
