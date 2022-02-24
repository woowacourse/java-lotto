package lotto.domain;

import java.util.Arrays;

public enum LottoPrize {

    MISS(0, 0, new Money(0)),
    FIFTH(3, 0, new Money(5000)),
    FOURTH(4, 0, new Money(50000)),
    THIRD(5, 0, new Money(1500000)),
    TWICE(5, 1, new Money(30000000)),
    FIRST(6, 0, new Money(2000000000));

    private final int lottoNumberMatches;
    private final int bonusNumberMatches;
    private final Money reward;

    LottoPrize(int lottoNumberMatches, int bonusNumberMatches, Money reward) {
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

    public Money getReward() {
        return reward;
    }

    public int getTotalReward(int number) {
        return reward.get() * number;
    }
}
