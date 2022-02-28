package lotto.domain;

import java.util.Arrays;
import lotto.domain.vo.Money;

public enum LottoPrize {

    MISS(0, new Money(0)),
    FIFTH(3, new Money(5000)),
    FOURTH(4, new Money(50000)),
    THIRD(5, new Money(1500000)),
    TWICE(5, new Money(30000000)),
    FIRST(6, new Money(2000000000));

    private final int lottoNumberMatchCount;
    private final Money reward;

    LottoPrize(int lottoNumberMatchCount, Money reward) {
        this.lottoNumberMatchCount = lottoNumberMatchCount;
        this.reward = reward;
    }

    public static LottoPrize match(int lottoNumberMatchCount, boolean bonusNumberMatch) {
        if (isTwice(lottoNumberMatchCount, bonusNumberMatch)) {
            return TWICE;
        }
        return Arrays.stream(LottoPrize.values())
                .filter(prize -> prize.lottoNumberMatchCount == lottoNumberMatchCount)
                .filter(prize -> !prize.equals(TWICE))
                .findFirst()
                .orElse(MISS);
    }

    private static boolean isTwice(int lottoNumberMatchCount, boolean bonusNumberMatch) {
        return lottoNumberMatchCount == TWICE.lottoNumberMatchCount && bonusNumberMatch;
    }

    public int getLottoNumberMatchCount() {
        return lottoNumberMatchCount;
    }

    public Money getReward() {
        return reward;
    }

    public int getTotalReward(int number) {
        return reward.get() * number;
    }
}
