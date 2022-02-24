package lotto.domain.lottonumber;

import lotto.domain.matchkind.LottoMatchKind;
import lotto.domain.lottonumber.vo.LottoNumber;

public class WinningNumbers {
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
        if (matchedCount < 3) {
            return LottoMatchKind.LOWER_THAN_THREE;
        }
        return LottoMatchKind.from(matchedCount, numbers.hasSameNumberWith(bonusNumber));
    }
}
