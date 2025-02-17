package lotto.constant;

import java.util.Arrays;

public enum WinningTier {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 3_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    EMPTY(0, false, 0);

    private final int matches;
    private final boolean needsBonus;
    private final int prize;

    WinningTier(int matches, boolean needsBonus, int prize) {
        this.matches = matches;
        this.needsBonus = needsBonus;
        this.prize = prize;
    }

    private static boolean meetsBonusRequirement(WinningTier tier, boolean hasBonusMatch) {
        if (tier.getNeedsBonus()) {
            return hasBonusMatch;
        }
        return true;
    }

    public int getMatches() {
        return matches;
    }

    public boolean getNeedsBonus() {
        return needsBonus;
    }

    public int getPrize() {
        return prize;
    }

    public static WinningTier find(int matches, boolean hasBonusMatch) {
        return Arrays.stream(WinningTier.values())
                .filter(tier -> tier.getMatches() == matches && meetsBonusRequirement(tier, hasBonusMatch))
                .findFirst()
                .orElse(WinningTier.EMPTY);
    }
}
