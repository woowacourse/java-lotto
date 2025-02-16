package lotto.domain;

import static lotto.util.Constant.LOTTO_WINNING_NUMBERS_HIT_STANDARD;

import java.util.Arrays;

public enum LottoPrize {

    MISS(0, false, 0),
    FIFTH(3, false, 5_000),
    FOURTH(4, false, 50_000),
    THIRD(5, false, 1_500_000),
    SECOND(5, true, 30_000_000),
    FIRST(6, false, 2_000_000_000);

    private final int hitNumbers;
    private final boolean hitBonus;
    private final int prize;

    LottoPrize(int hitNumbers, boolean hitBonus, int prize) {
        this.hitNumbers = hitNumbers;
        this.hitBonus = hitBonus;
        this.prize = prize;
    }

    public static LottoPrize findLottoPrize(int winningNumbersHit, boolean isBonusHit) {
        return Arrays.stream(values())
                .filter(rank -> rank.hitNumbers == winningNumbersHit)
                .filter(rank -> checkBonusHit(rank, isBonusHit))
                .findFirst()
                .orElse(MISS);
    }

    private static boolean checkBonusHit(LottoPrize lottoPrize, boolean isBonusHit) {
        if (lottoPrize.hitNumbers == LOTTO_WINNING_NUMBERS_HIT_STANDARD) {
            return lottoPrize.hitBonus == isBonusHit;
        }
        return true;
    }

    public int getHitNumbers() {
        return hitNumbers;
    }

    public int getPrize() {
        return prize;
    }
}
