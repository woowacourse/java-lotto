package domain.enums;

import java.util.Optional;

public enum Prize {
    MISS(0, 0, false),
    FIFTH( 5_000, 3, false),
    FOURTH(50_000, 4, false),
    THIRD( 150_000, 5, false),
    SECOND( 30_000_000, 5, true),
    FIRST(2_000_000_000, 6, false),
    ;

    private final int prizeMoney;
    private final int matchedCount;
    private final boolean isBonusRequired;

    public int getPrizeMoney() {
        return this.prizeMoney;
    }

    public int getMatchedCount() {
        return matchedCount;
    }

    public boolean isBonusRequired() {
        return isBonusRequired;
    }

    Prize(int prizeMoney, int matchedCount, boolean isBonusRequired) {
        this.prizeMoney = prizeMoney;
        this.matchedCount = matchedCount;
        this.isBonusRequired = isBonusRequired;
    }

    public static Prize getPrizeOf(int matchedCount, boolean isBonusMatched) {
        for (Prize prize : Prize.values()) {
            Optional<Prize> foundPrize = findPrize(matchedCount, isBonusMatched, prize);
            if (foundPrize.isEmpty()) {
                continue;
            }
            return foundPrize.get();
        }

        return Prize.MISS;
    }

    private static Optional<Prize> findPrize(int matchedCount, boolean isBonusMatched, Prize prize) {
        if (prize.matchedCount != matchedCount) {
            return Optional.empty();
        }
        if (isBonusMatched && prize.equals(Prize.THIRD)) {
            return Optional.of(Prize.SECOND);
        }

        return Optional.of(prize);
    }
}
