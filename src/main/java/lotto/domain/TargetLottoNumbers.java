package lotto.domain;

import lotto.domain.vo.LottoNumber;

public class TargetLottoNumbers {
    private static final String TARGET_BONUS_DUPLICATION_EXCEPTION_MESSAGE =
            "당첨 번호와 보너스 번호에 중복이 있으면 안됩니다.";

    private final LottoNumbers targetLottoNumbers;
    private final LottoNumber bonusLottoNumber;

    public TargetLottoNumbers(final LottoNumbers targetLottoNumbers, final LottoNumber bonusLottoNumber) {
        validateDuplication(targetLottoNumbers, bonusLottoNumber);
        this.targetLottoNumbers = targetLottoNumbers;
        this.bonusLottoNumber = bonusLottoNumber;
    }

    private void validateDuplication(final LottoNumbers targetLottoNumbers, final LottoNumber bonusLottoNumber) {
        if (targetLottoNumbers.hasSameNumberWith(bonusLottoNumber)) {
            throw new IllegalArgumentException(TARGET_BONUS_DUPLICATION_EXCEPTION_MESSAGE);
        }
    }

    public LottoMatchKind getLottoMatchResult(final LottoNumbers lottoNumbers) {
        final int matchedCount = lottoNumbers.getMatchCount(targetLottoNumbers);
        if (matchedCount < 3) {
            return LottoMatchKind.LOWER_THAN_THREE;
        }
        return LottoMatchKind.from(matchedCount, lottoNumbers.hasSameNumberWith(bonusLottoNumber));
    }
}
