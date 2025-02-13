package domain.enums;

import java.util.Optional;

public enum Prize {
    EMPTY("", 0, 0, false),
    FIFTH("3개 일치", 5_000, 3, false),
    FOURTH("4개 일치", 50_000, 4, false),
    THIRD("5개 일치", 150_000, 5, false),
    SECOND("5개 일치, 보너스 볼 일치", 30_000_000, 5, true),
    FIRST("6개 일치", 2_000_000_000, 6, false),
    ;

    private String matchedMessage;
    private int prizeMoney;
    private int matchedCount;
    private boolean isBonusMatched;

    Prize(String matchedMessage, int prizeMoney, int matchedCount, boolean isBonusMatched) {
        this.matchedMessage = matchedMessage;
        this.prizeMoney = prizeMoney;
        this.matchedCount = matchedCount;
        this.isBonusMatched = isBonusMatched;
    }

    public int getPrizeMoney() {
        return this.prizeMoney;
    }

    public Prize getPrizeOf(int matchedCount, boolean isBonusMatched) {
        for (Prize prize : Prize.values()) {
            Optional<Prize> foundPrize = findPrize(matchedCount, isBonusMatched, prize);
            if (foundPrize.isPresent()) {
                return foundPrize.get();
            }
        }
        return Prize.EMPTY;
    }

    private Optional<Prize> findPrize(int matchedCount, boolean isBonusMatched, Prize prize) {
        if (prize.matchedCount == matchedCount) {
            if (prize.equals(Prize.THIRD) && isBonusMatched) {
                return Prize.SECOND;
            }
            return prize;
        }
        return ;
    }
}
