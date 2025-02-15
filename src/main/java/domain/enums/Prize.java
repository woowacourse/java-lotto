package domain.enums;

import java.util.Optional;

public enum Prize {
    MISS("", 0, 0),
    FIFTH("3개 일치", 5_000, 3),
    FOURTH("4개 일치", 50_000, 4),
    THIRD("5개 일치", 150_000, 5),
    SECOND("5개 일치, 보너스 볼 일치", 30_000_000, 5),
    FIRST("6개 일치", 2_000_000_000, 6),
    ;

    private final String matchedMessage;
    private final int prizeMoney;
    private final int matchedCount;

    public String getMatchedMessage() {
        return matchedMessage;
    }

    public int getPrizeMoney() {
        return this.prizeMoney;
    }

    Prize(String matchedMessage, int prizeMoney, int matchedCount) {
        this.matchedMessage = matchedMessage;
        this.prizeMoney = prizeMoney;
        this.matchedCount = matchedCount;
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
