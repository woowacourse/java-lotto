package domain.enums;

import java.util.Optional;

public enum Prize {
    EMPTY("", 0, 0),
    FIFTH("3개 일치", 5_000, 3),
    FOURTH("4개 일치", 50_000, 4),
    THIRD("5개 일치", 150_000, 5),
    SECOND("5개 일치, 보너스 볼 일치", 30_000_000, 5),
    FIRST("6개 일치", 2_000_000_000, 6),
    ;

    private String matchedMessage;
    private int prizeMoney;
    private int matchedCount;

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
            if (foundPrize.isPresent()) {
                return foundPrize.get();
            }
        }
        return Prize.EMPTY;
    }

    private static Optional<Prize> findPrize(int matchedCount, boolean isBonusMatched, Prize prize) {
        if (prize.matchedCount == matchedCount) {
            if (prize.equals(Prize.THIRD) && isBonusMatched) {
                return Optional.of(Prize.SECOND);
            }
            return Optional.of(prize);
        }
        return Optional.empty();
    }
}
