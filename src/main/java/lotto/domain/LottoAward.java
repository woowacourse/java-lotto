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
    private final boolean matchesBonusNumber;

    LottoAward(final int matchingCount, final int amount, final boolean matchesBonusNumber) {
        this.matchingCount = matchingCount;
        this.amount = amount;
        this.matchesBonusNumber = matchesBonusNumber;
    }

    public static LottoAward from(final int inputMatchingCount, final boolean isBonusNumberMatched) {
        return Arrays.stream(LottoAward.values())
                .filter(lottoAward -> isLottoAwardMatch(inputMatchingCount, isBonusNumberMatched, lottoAward))
                .findFirst()
                .orElse(NONE);
    }

    private static boolean isLottoAwardMatch(final int inputMatchingCount, final boolean matchesBonusNumber,
                                             final LottoAward lottoAward) {
        return lottoAward.matchingCount == inputMatchingCount
                && lottoAward.matchesBonusNumber == matchesBonusNumber;
    }

    public int getMatchingCount() {
        return matchingCount;
    }

    public int getAmount() {
        return amount;
    }
}
