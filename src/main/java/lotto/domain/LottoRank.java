package lotto.domain;

public enum LottoRank {
    MISS(0, 0),
    FIFTH(3, 500),
    FOURTH(4, 5_000),
    THIRD(5, 1_500_000),
    SECOND(5, 30_000_000),
    FIRST(6, 2_000_000_000);

    private static final int WINNING_CONDITION = 3;
    private static final int COUNT_OF_MATCH_SECOND_THIRD = 5;

    private int countOfMatch;
    private int rewardMoney;

    LottoRank(final int countOfMatch, final int rewardMoney) {
        this.countOfMatch = countOfMatch;
        this.rewardMoney = rewardMoney;
    }

    public int getCountOfMatch() {
        return countOfMatch;
    }

    public int getRewardMoney() {
        return rewardMoney;
    }

    public static LottoRank valueOf(final int countOfMatch, final boolean hasBonusBall) {
        if (countOfMatch < WINNING_CONDITION) {
            return MISS;
        }
        if (countOfMatch == COUNT_OF_MATCH_SECOND_THIRD && hasBonusBall) {
            return SECOND;
        }
        for (LottoRank lottoRank : values()) {
            if (lottoRank.countOfMatch == countOfMatch) {
                return lottoRank;
            }
        }
        throw new IllegalArgumentException("유효하지 않은 값입니다.");
    }
}
