package lotto.domain;

import java.util.Arrays;

public enum WinningInfo {
    FAIL(0, false, 0),
    FIFTH(3, false, 5_000),
    FOURTH(4, false, 50_000),
    THIRD(5, false, 1_500_000),
    SECOND(5, true, 30_000_000),
    FIRST(6, false, 2_000_000_000);

    private int winningCount;
    private boolean hasBonus;
    private int winningPrice;

    WinningInfo(int winningCount, boolean hasBonus, int winningPrice) {
        this.winningCount = winningCount;
        this.hasBonus = hasBonus;
        this.winningPrice = winningPrice;
    }

    public static WinningInfo valueOf(int winningCount, boolean hasBonus) {
        if (winningCount == 5 && hasBonus) {
            return WinningInfo.SECOND;
        }
        return Arrays.stream(values())
                .filter(o -> o.winningCount == winningCount)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public int getWinningCount() {
        return winningCount;
    }

    public int getWinningPrice() {
        return winningPrice;
    }

    public boolean hasBonus() {
        return hasBonus;
    }
}
