package lotto.domain;

import java.util.Arrays;

public enum PrizeGroup {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    SIXTH(0, 0);

    private final int matchNumberCount;
    private final int money;

    PrizeGroup(int matchNumberCount, int money) {
        this.matchNumberCount = matchNumberCount;
        this.money = money;
    }

    public static PrizeGroup findPrizeByLottoResult(LottoResult result) {
        PrizeGroup prize = Arrays.stream(PrizeGroup.values())
                .filter(aPrize -> result.isEqualToMatchCount(aPrize.matchNumberCount))
                .findFirst()
                .orElse(SIXTH);

        if (prize.isThird(result)) {
            return THIRD;
        }
        return prize;
    }

    private boolean isThird(LottoResult lottoResult) {
        return this == SECOND && !lottoResult.isBonusMatch();
    }

    public int getMoney() {
        return money;
    }
}
