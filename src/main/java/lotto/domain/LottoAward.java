package lotto.domain;

import java.util.Arrays;
import java.util.List;

public enum LottoAward {

    FIFTH_RANK(3, 5_000, false),
    FOURTH_RANK(4, 50_000, false),
    THIRD_RANK(5, 1_500_000, false),
    SECOND_RANK(5, 30_000_000, true),
    FIRST_RANK(6, 2_000_000_000, false),
    NONE(0, 0, false);

    public static final List<LottoAward> ACTUAL_LOTTO_AWARD = Arrays.stream(LottoAward.values())
            .filter(lottoAward -> !lottoAward.equals(NONE))
            .toList();

    private final int matchingCount;
    private final int amount;
    private final boolean isBonusNumberMatched;

    LottoAward(final int matchingCount, final int amount, final boolean isBonusNumberMatched) {
        this.matchingCount = matchingCount;
        this.amount = amount;
        this.isBonusNumberMatched = isBonusNumberMatched;
    }

    public static LottoAward from(final int inputMatchingCount, final boolean isBonusNumberMatched) {
        return Arrays.stream(LottoAward.values())
                .filter(lottoAward -> isMatched(inputMatchingCount, isBonusNumberMatched, lottoAward))
                .findFirst()
                .orElse(NONE);
    }

    private static boolean isMatched(final int inputMatchingCount, final boolean isBonusNumberMatched,
                                     final LottoAward lottoAward) {
        return lottoAward.matchingCount == inputMatchingCount
                && lottoAward.isBonusNumberMatched == isBonusNumberMatched;
    }

    public int getMatchingCount() {
        return matchingCount;
    }

    public int getAmount() {
        return amount;
    }
}
