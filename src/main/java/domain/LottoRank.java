package domain;

import java.util.Arrays;

public enum LottoRank {

    FAIL(0, -1, false),
    FIFTH(5_000, 3, false),
    FOURTH(50_000, 4, false),
    THIRD(1_500_000, 5, false),
    SECOND(30_000_000, 5, true),
    FIRST(2_000_000_000, 6, false);

    private final long amount;
    private final int matchCount;
    private final boolean hasBonusNumber;

    LottoRank(long amount, int matchCount, boolean hasBonusNumber) {
        this.amount = amount;
        this.matchCount = matchCount;
        this.hasBonusNumber = hasBonusNumber;
    }

    public static LottoRank findRank(int count, boolean bonusNumber) {
        if (isMatchFiveNumbers(count)) {
            return checkSecondOrThird(bonusNumber);
        }
        return Arrays.stream(LottoRank.values())
                .filter(rank -> rank.getMatchCount() == count)
                .findFirst()
                .orElse(FAIL);
    }

    private static boolean isMatchFiveNumbers(int matchCount) {
        return matchCount == 5;
    }

    private static LottoRank checkSecondOrThird(boolean bonusNumber) {
        if (bonusNumber) {
            return SECOND;
        }
        return THIRD;
    }

    public long getAmount() {
        return amount;
    }

    public int getMatchCount() {
        return matchCount;
    }
}
