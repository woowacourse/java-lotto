package lotto.domain;

import java.util.List;

public enum WinningTier {
    FIRST(new WinningCondition(6, false), 2000000000),
    SECOND(new WinningCondition(5, true), 3000000),
    THIRD(new WinningCondition(5, false), 1500000),
    FOURTH(new WinningCondition(4, false), 50000),
    FIFTH(new WinningCondition(3, false), 5000),
    EMPTY(new WinningCondition(0, false), 0);

    private final WinningCondition condition;
    private final int prize;

    WinningTier(WinningCondition condition, int prize) {
        this.condition = condition;
        this.prize = prize;
    }

    public WinningCondition getCondition() {
        return condition;
    }

    public int getPrize() {
        return prize;
    }

    public static WinningTier find(int matchedCount, boolean isBonusNumberMatched) {
        List<WinningTier> allTiers = List.of(WinningTier.values());
        return allTiers.stream()
                .filter(tier -> tier.getCondition().isWinningCondition(matchedCount, isBonusNumberMatched))
                .findFirst()
                .orElse(WinningTier.EMPTY);
    }
}
