package model;

import java.util.HashSet;
import java.util.List;

public class UserLotto {

    private Lotto winningNumbers;

    public UserLotto(List<Integer> userInputNumbers) {
        this.winningNumbers = new Lotto(new HashSet<>(userInputNumbers));
    }

    public void isDuplicateBonusNumber(int bonusNumber) {
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

}
