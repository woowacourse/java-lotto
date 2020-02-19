package lotto.domain;

import java.util.List;

public class WinningLotto extends Lotto {

    private int bonusNumber;

    public WinningLotto(List<Integer> numbers, int bonusNumber) {
        super(numbers);
        validateBonusNumberScope(bonusNumber);
        validateBonusNumberDuplication(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    public static void validateBonusNumberScope(int bonusNumber) {
        validateNumberScope(bonusNumber);
    }

    public void validateBonusNumberDuplication(int bonusNumber) {
        if (lottoNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException();
        }
    }

    public boolean isBonusNumber(List<Integer> lotto) {
        return lotto.contains(this.bonusNumber);
    }
}