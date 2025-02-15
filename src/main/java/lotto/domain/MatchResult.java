package lotto.domain;

import static lotto.common.constant.Constant.LOTTO_NUMBER_COUNT;
import static lotto.common.exception.ErrorMessage.ERROR_MATCH_NUMBER_NOT_VALID;

public record MatchResult(int matchCount, boolean isBonusMatched) {
    private static final int MIN_MATCH_NUMBER = 0;

    public MatchResult {
        validateMatchNumber(matchCount);
    }

    private static void validateMatchNumber(int matchCount) {
        if(matchCount < MIN_MATCH_NUMBER || matchCount > LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(ERROR_MATCH_NUMBER_NOT_VALID);
        }
    }
}
