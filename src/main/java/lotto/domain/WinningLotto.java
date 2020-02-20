package lotto.domain;

import java.util.List;

public class WinningLotto extends Lotto {
    private static final String BONUS_NUMBER_DUPLICATION_ERROR_MESSAGE = "보너스 번호가 당첨 번호와 같습니다.";

    private int bonusNumber;

    public WinningLotto(List<Integer> numbers, int bonusNumber) {
        super(numbers);
        validateBonusNumberScope(bonusNumber);
        validateBonusNumberDuplication(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    static void validateBonusNumberScope(int bonusNumber) {
        validateNumberScope(bonusNumber);
    }

    void validateBonusNumberDuplication(int bonusNumber) {
        if (lottoNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(BONUS_NUMBER_DUPLICATION_ERROR_MESSAGE);
        }
    }

    public boolean isBonusNumber(Lotto lotto) {
        return lotto.getLottoNumbers()
                .contains(this.bonusNumber);
    }
}