package lotto.constant;

import java.util.List;

public enum WinningTier {
    FIRST(6, false, 2000000000),
    SECOND(5, true, 3000000),
    THIRD(5, false, 1500000),
    FOURTH(4, false, 50000),
    FIFTH(3, false, 5000),
    EMPTY(0, false, 0);

    private final int matches;
    private final boolean hasBonusMatch;
    private final int prize;

    WinningTier(int matches, boolean hasBonusMatch, int prize) {
        this.matches = matches;
        this.hasBonusMatch = hasBonusMatch;
        this.prize = prize;
    }

    public int getMatches() {
        return matches;
    }

    public boolean getHasBonusMatch() {
        return hasBonusMatch;
    }

    public int getPrize() {
        return prize;
    }

    public static WinningTier find(int matches, boolean hasBonusMatch) {
        List<WinningTier> allTiers = List.of(WinningTier.values());
        return allTiers.stream()
                .filter(tier -> tier.getMatches() == matches && tier.getHasBonusMatch() == hasBonusMatch)
                .findFirst()
                .orElse(WinningTier.EMPTY);
    }
}
