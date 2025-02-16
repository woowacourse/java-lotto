package model;

import java.util.HashSet;
import java.util.Set;

public class UserLotto {

    private final Lotto winningNumbers;
    private final BonusNumber bonusNumber;

    public UserLotto(Set<Integer> userInputNumbers, BonusNumber bonusNumber) {
        this.winningNumbers = new Lotto(new HashSet<>(userInputNumbers));
        validateDuplicateBonusNumber(bonusNumber.getBonusNumber());
        this.bonusNumber = bonusNumber;
    }

    private void validateDuplicateBonusNumber(int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호와 당첨 번호는 중복되어서는 안됩니다.");
        }
    }

    public int calculateMatchCount(Lotto lotto) {
        return (int) winningNumbers.getRandomNumbers()
                .stream()
                .filter(lotto::contains)
                .count();
    }

    public boolean isBonusMatch(Lotto lotto) {
        return bonusNumber.isBonusMatch(lotto);
    }

}
