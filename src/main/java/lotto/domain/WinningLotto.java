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
            throw new IllegalArgumentException("보너스 번호는 당첨번호와 중복될 수 없습니다.");
        }
    }

    public boolean isBonusNumber(List<Integer> lotto) {
        return lotto.contains(this.bonusNumber);
    }
}