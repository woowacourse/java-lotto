package lotto.domain;

public enum LottoRank {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 5_000),
    FIFTH(3, 500),
    MISS(0, 0);

    private static final int WINNING_CONDITION = 3;

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
        if (countOfMatch == 5 && !hasBonusBall) {
            return THIRD;
        }
        for (LottoRank lottoRank : values()) {
            if (lottoRank.countOfMatch == countOfMatch) {
                return lottoRank;
            }
        }
        throw new IllegalArgumentException("유효하지 않은 값입니다.");
    }
}
