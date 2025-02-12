package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class UserLotto {
    private List<Integer> winningNumbers;

    public UserLotto(List<Integer> winningNumbers) {
        validateSize(winningNumbers);
        validateDuplicate(winningNumbers);
        validateNumberRange(winningNumbers);

        this.winningNumbers = winningNumbers;
    }

    private void validateSize(List<Integer> winningNumbers) {
        if (winningNumbers.size() != 6) {
            throw new IllegalArgumentException("6개의 숫자만 입력해주세요.");
        }
    }

    private void validateDuplicate(List<Integer> winningNumbers) {
        if (new HashSet<>(winningNumbers).size() != winningNumbers.size()) {
            throw new IllegalArgumentException("숫자는 중복될 수 없습니다.");
        }
    }

    private void validateNumberRange(List<Integer> winningNumbers) {
        for (Integer winningNumber : winningNumbers) {
            if (winningNumber < 1 || winningNumber > 45) {
                throw new IllegalArgumentException("숫자는 1~45 사이여야 합니다.");
            }
        }
    }

    public void isDuplicateBonusNumber(int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호와 당첨 번호는 중복되어서는 안됩니다.");
        }
    }

}
