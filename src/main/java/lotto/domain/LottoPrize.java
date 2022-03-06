package lotto.domain;

import java.util.Arrays;

public enum LottoPrize {

    MISS(0, 0),
    FIFTH(3, 5_000),
    FOURTH(4, 50_000),
    THIRD(5, 1_500_000),
    SECOND(5, 30_000_000),
    FIRST(6, 2_000_000_000);

    private final int lottoNumberMatchCount;
    private final int reward;

    LottoPrize(int lottoNumberMatchCount, int reward) {
        this.lottoNumberMatchCount = lottoNumberMatchCount;
        this.reward = reward;
    }

    public static LottoPrize match(int lottoNumberMatches, boolean bonusNumberMatch) {
        if (lottoNumberMatches == SECOND.lottoNumberMatchCount) {
            return getSecondOrThirdPrize(bonusNumberMatch);
        }

        return Arrays.stream(LottoPrize.values())
                .filter(prize -> prize.lottoNumberMatchCount == lottoNumberMatches)
                .findFirst()
                .orElse(MISS);

    }

    private static LottoPrize getSecondOrThirdPrize(boolean bonusNumberMatch) {
        if (bonusNumberMatch) {
            return SECOND;
        }
        return THIRD;
    }

    public int getLottoNumberMatchCount() {
        return lottoNumberMatchCount;
    }

    public int getReward() {
        return reward;
    }

    public int getTotalReward(int number) {
        return reward * number;
    }
}
