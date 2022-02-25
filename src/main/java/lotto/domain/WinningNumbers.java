package lotto.domain;

import lotto.domain.vo.LottoNumber;

public class WinningNumbers {
    public static final int WINNING_WITH_BONUS_MATCH_COUNTS = 5;
    public static final int WINNING_MINIMUM_MATCH_COUNTS = 3;
    private static final String TARGET_BONUS_DUPLICATION_EXCEPTION_MESSAGE =
            "당첨 번호와 보너스 번호에 중복이 있으면 안됩니다.";
    private final LottoNumbers lastWinningNumbers;
    private final LottoNumber bonusNumber;

    public WinningNumbers(final LottoNumbers lastWinningNumbers, final LottoNumber bonusNumber) {
        validateDuplication(lastWinningNumbers, bonusNumber);
        this.lastWinningNumbers = lastWinningNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validateDuplication(final LottoNumbers lastWinningNumbers, final LottoNumber bonusNumber) {
        if (lastWinningNumbers.hasSameNumberWith(bonusNumber)) {
            throw new IllegalArgumentException(TARGET_BONUS_DUPLICATION_EXCEPTION_MESSAGE);
        }
    }

    public LottoMatchKind getLottoMatchResult(final LottoNumbers numbers) {
        final int matchedCount = numbers.getMatchCount(lastWinningNumbers);
        if (getBlankMatchResult(numbers, matchedCount)) {
            return LottoMatchKind.BLANK;
        }
        return LottoMatchKind.from(matchedCount, numbers.hasSameNumberWith(bonusNumber));
    }

    private boolean getBlankMatchResult(LottoNumbers numbers, int matchedCount) {
        if (matchedCount < WINNING_MINIMUM_MATCH_COUNTS) {
            return true;
        }
        return numbers.hasSameNumberWith(bonusNumber) && matchedCount != WINNING_WITH_BONUS_MATCH_COUNTS;
    }
}
