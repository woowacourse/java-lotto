package lotto.domain;

import java.util.Arrays;
import java.util.List;

public enum LottoAward {

    FIFTH_RANK(3, 5_000),
    FOURTH_RANK(4, 50_000),
    THIRD_RANK(5, 1_500_000),
    SECOND_RANK(5, 30_000_000),
    FIRST_RANK(6, 2_000_000_000),
    NONE(0, 0);

    public static final List<LottoAward> ACTUAL_LOTTO_AWARD = Arrays.stream(LottoAward.values())
            .filter(lottoAward -> !lottoAward.equals(NONE))
            .toList();

    private final int matchingCount;
    private final int amount;

    LottoAward(final int matchingCount, final int amount) {
        this.matchingCount = matchingCount;
        this.amount = amount;
    }

    public static LottoAward from(final int inputMatchingCount, final boolean matchesBonusNumber) {
        if (inputMatchingCount == SECOND_RANK.matchingCount && matchesBonusNumber) {
            return SECOND_RANK;
        }
        return Arrays.stream(LottoAward.values())
                .filter(lottoAward -> lottoAward.matchingCount == inputMatchingCount)
                .findFirst()
                .orElse(NONE);
    }

    public int getMatchingCount() {
        return matchingCount;
    }

    public int getAmount() {
        return amount;
    }
}
