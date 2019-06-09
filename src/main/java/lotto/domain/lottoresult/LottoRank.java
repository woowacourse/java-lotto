package lotto.domain.lottoresult;

import java.util.Arrays;

public enum LottoRank {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    FAIL(0, false, 0);

    private final int countOfMatch;
    private final boolean bonusMatch;
    private final int reward;

    LottoRank(int countOfMatch, boolean bonusMatch, int reward) {
        this.countOfMatch = countOfMatch;
        this.bonusMatch = bonusMatch;
        this.reward = reward;
    }

    public static LottoRank rankOf(int countOfMatch, boolean bonusMatch) {
        return Arrays.stream(values())
                .filter(rank -> isEqualCountOfMatch(rank, countOfMatch))
                .filter(rank -> isBonusMatched(rank, bonusMatch))
                .findAny()
                .orElse(FAIL);
    }

    private static boolean isEqualCountOfMatch(LottoRank rank, int countOfMatch) {
        return rank.countOfMatch == countOfMatch;
    }

    private static boolean isBonusMatched(LottoRank rank, boolean bonusMatch) {
        return rank.isBonusMatch() ? bonusMatch : true;
    }

    public int getReward() {
        return reward;
    }

    public int getCountOfMatch() {
        return countOfMatch;
    }

    public boolean isBonusMatch() {
        return bonusMatch;
    }
}
