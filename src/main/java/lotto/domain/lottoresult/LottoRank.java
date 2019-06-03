package lotto.domain.lottoresult;

import java.util.Arrays;

public enum LottoRank {
    FIRST(6, true, 2_000_000_000),
    SECOND(5, false, 30_000_000),
    THIRD(5, true, 1_500_000),
    FOURTH(4, true, 50_000),
    FIFTH(3, true, 5_000),
    FAIL(0, true, 0);

    private static final int WINNING_MIN_COUNT = 3;

    private final int countOfMatch;
    private final boolean bonusMatchChecker;
    private final int reward;

    LottoRank(int countOfMatch, boolean bonusMatchChecker, int reward) {
        this.countOfMatch = countOfMatch;
        this.bonusMatchChecker = bonusMatchChecker;
        this.reward = reward;
    }

    public static LottoRank rankOf(int countOfMatch, boolean bonusMatch) {
        if (countOfMatch < WINNING_MIN_COUNT) {
            return FAIL;
        }

        return Arrays.stream(values())
                .filter(rank -> matchRank(rank, countOfMatch, bonusMatch))
                .findAny()
                .get();
    }

    private static boolean matchRank(LottoRank rank, int countOfMatch, boolean bonusMatch) {
        return (countOfMatch == rank.countOfMatch) && (rank.bonusMatchChecker || bonusMatch);
    }

    public int getReward() {
        return reward;
    }

    public int getCountOfMatch() {
        return countOfMatch;
    }

    public boolean isBonusMatch() {
        return this.equals(LottoRank.SECOND);
    }
}
