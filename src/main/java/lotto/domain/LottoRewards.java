package lotto.domain;

public enum LottoRewards {
    FIRST(6, 2_000_000_000),
    SECOND(5, 1_500_000),
    THRID(4, 5_000),
    FOURTH(3, 500),
    MISS(0, 0);

    private int countOfMatch;
    private int rewardMoney;

    LottoRewards(int countOfMatch, int rewardMoney) {
        this.countOfMatch = countOfMatch;
        this.rewardMoney = rewardMoney;
    }

    public int getCountOfMatch() {
        return countOfMatch;
    }

    public int getRewardMoney() {
        return rewardMoney;
    }

    public static LottoRewards valueOf(int countOfMatch) {
        if (countOfMatch < 3) {
            return MISS;
        }
        for (LottoRewards lottoRewards : values()) {
            if (lottoRewards.countOfMatch == countOfMatch) {
                return lottoRewards;
            }
        }
        throw new IllegalArgumentException("유효하지 않은 값입니다.");
    }
}
