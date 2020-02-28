package domain.lotto.lottoresult;

import java.util.Arrays;

public enum LottoRank {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    NOTHING(-1, false, 0);

    private final int hitCount;
    private final boolean bonus;
    private final int winning;

    LottoRank(int hitCount, boolean bonus, int winning) {
        this.hitCount = hitCount;
        this.bonus = bonus;
        this.winning = winning;
    }

    private static LottoRank calculateRankWithoutBonus(int hitCount) {
        return Arrays.stream(LottoRank.values())
                .filter(rank -> rank.hitCount == hitCount && !rank.bonus)
                .findAny()
                .orElse(NOTHING);
    }

    public static LottoRank calculateRank(int hitCount, boolean bonus) {
        return Arrays.stream(LottoRank.values())
                .filter(rank -> rank.bonus == bonus && rank.hitCount == hitCount)
                .findAny()
                .orElseGet(() -> calculateRankWithoutBonus(hitCount));
    }

    public long multiplyCount(ResultCount resultCount) {
        return resultCount.multiply(winning);
    }

    public int getHitCount() {
        return hitCount;
    }

    public int getWinning() {
        return winning;
    }

    public boolean hasBonus() {
        return bonus;
    }
}
