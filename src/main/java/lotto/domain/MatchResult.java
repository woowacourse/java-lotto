package lotto.domain;

import static lotto.common.exception.ErrorMessage.ERROR_MATCH_NUMBER_NOT_VALID;
import static lotto.domain.Lotto.LOTTO_SIZE;

public record MatchResult(int matchCount, boolean isBonusMatched) {
}
