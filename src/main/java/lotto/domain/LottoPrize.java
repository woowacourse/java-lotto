package lotto.domain;

import java.util.Arrays;

public enum LottoPrize {

    MISS(0, 0, 0),
    FIFTH(3, 0, 5_000),
    FOURTH(4, 0, 50_000),
    THIRD(5, 0, 1_500_000),
    TWICE(5, 1, 30_000_000),
    FIRST(6, 0, 2_000_000_000);

    private final int lottoNumberMatches;
    private final int bonusNumberMatches;
    private final int reward;

    LottoPrize(int lottoNumberMatches, int bonusNumberMatches, int reward) {
        this.lottoNumberMatches = lottoNumberMatches;
        this.bonusNumberMatches = bonusNumberMatches;
        this.reward = reward;
    }

    public static LottoPrize match(int lottoNumberMatches, int bonusNumberMatches) {
        return Arrays.stream(LottoPrize.values())
                .filter(prize -> prize.lottoNumberMatches == lottoNumberMatches)
                .filter(prize -> !(prize.equals(THIRD)) || prize.bonusNumberMatches == bonusNumberMatches)
                .findFirst()
                .orElse(MISS);
    }

    public int getLottoNumberMatches() {
        return lottoNumberMatches;
    }

    public int getReward() {
        return reward;
    }

    public int getTotalReward(int number) {
        return reward * number;
    }
}
