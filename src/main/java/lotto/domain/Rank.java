package lotto.domain;

import java.util.Arrays;

public enum Rank {

    MISS(0, 0, "3개 미만 일치"),
    FIFTH(3, 5_000, "3개 일치"),
    FOURTH(4, 50_000, "4개 일치"),
    THIRD(5, 1_500_000, "5개 일치"),
    SECOND(5, 30_000_000, "5개 일치, 보너스 볼 일치"),
    FIRST(6, 2_000_000_000, "6개 일치");

    private final int matchCount;
    private final int reward;
    private final String matchStatus;

    Rank(final int matchCount, final int reward, final String matchStatus) {
        this.matchCount = matchCount;
        this.reward = reward;
        this.matchStatus = matchStatus;
    }

    public static Rank matchResult(int matchCount, boolean matchBonusNumber) {
        if (isSecondRank(matchCount, matchBonusNumber)) {
            return Rank.SECOND;
        }
        if (isThirdRank(matchCount, matchBonusNumber)) {
            return Rank.THIRD;
        }
        return getRank(matchCount);
    }

    public int calculateTotalReward(Integer count) {
        return reward * count;
    }

    private static boolean isSecondRank(int matchCount, boolean matchBonusNumber) {
        return matchCount == Rank.THIRD.matchCount && matchBonusNumber;
    }

    private static boolean isThirdRank(int matchCount, boolean matchBonusNumber) {
        return matchCount == Rank.THIRD.matchCount && !matchBonusNumber;
    }

    private static Rank getRank(int matchCount) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.matchCount == matchCount)
                .findFirst()
                .orElse(Rank.MISS);
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getReward() {
        return reward;
    }

    public String getMatchStatus() {
        return matchStatus;
    }
}

