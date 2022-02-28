package lotto.model.prize;

import java.util.Arrays;

/*
 * 등수별 상금과 매칭 정보를 가지는 Enum Class
 */
public enum Prize {
    FIRST(6, 2_000_000_000, false),
    SECOND(5, 30_000_000, true),
    THIRD(5, 1_500_000, false),
    FOURTH(4, 50_000, false),
    FIFTH(3, 5_000, false),
    NONE(0, 0, false);

    private final int matchCount;
    private final long amount;
    private final boolean bonus;

    Prize(int matchCount, long amount, boolean bonus) {
        this.matchCount = matchCount;
        this.amount = amount;
        this.bonus = bonus;
    }

    public static Prize getPrize(MatchResult matchResult) {
        if (!matchResult.isCount(SECOND.matchCount)) {
            return getPrizeByCount(matchResult);
        }
        if (matchResult.isBonus()) {
            return SECOND;
        }
        return THIRD;
    }

    private static Prize getPrizeByCount(MatchResult matchResult) {
        return Arrays.stream(values())
                .filter(prize -> matchResult.isCount(prize.matchCount))
                .findFirst()
                .orElse(NONE);
    }

    public long pickAmount(int count) {
        return this.amount * count;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public long getAmount() {
        return amount;
    }

    public boolean isBonus() {
        return bonus;
    }
}
