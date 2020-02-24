package lotto.domain;

import java.util.Arrays;

public enum WinningInfo {
    FAIL(0, false, 0),
    FIFTH(3, false, 5000),
    FOURTH(4, false, 50000),
    THIRD(5, false, 1500000),
    SECOND(5, true, 30000000),
    FIRST(6, false, 2000000000);

    private int matchCount;
    private boolean hasBonus;
    private int winningPrice;

    WinningInfo(int matchCount, boolean hasBonus, int winningPrice) {
        this.matchCount = matchCount;
        this.hasBonus = hasBonus;
        this.winningPrice = winningPrice;
    }

    public static WinningInfo valueOf(int matchCount, boolean hasBonus) {
        WinningInfo winningInfo = Arrays.stream(values())
                .filter(x -> x.matchCount == matchCount)
                .findFirst()
                .orElse(FAIL);

        if (hasBonus && (winningInfo == SECOND || winningInfo == THIRD)) {
            winningInfo = SECOND;
        }
        return winningInfo;
    }

    public int getWinningPrice(){
        return winningPrice;
    }

    public int getMatchCount() { return matchCount; }

    public boolean getHasBonus() {return hasBonus; }
}
